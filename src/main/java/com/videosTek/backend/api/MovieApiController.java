package com.videosTek.backend.api;


import com.videosTek.backend.entity.Mediatek;
import com.videosTek.backend.entity.Movie;
import com.videosTek.backend.entity.User;
import com.videosTek.backend.entity.dto.MovieDto;
import com.videosTek.backend.entity.response.ApiResponse;
import com.videosTek.backend.entity.response.EnumStatus;
import com.videosTek.backend.entity.response.PaginateResponse;
import com.videosTek.backend.service.btc.BtcServiceImpl;
import com.videosTek.backend.service.genre.GenreServiceImpl;
import com.videosTek.backend.service.mediatek.MediatekServiceImpl;
import com.videosTek.backend.service.movie.MovieServiceImpl;
import com.videosTek.backend.service.user.UserServiceImpl;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;


@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "api/movie", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieApiController {

    private final MovieServiceImpl movieService;
    private final GenreServiceImpl genreService;
    private final MediatekServiceImpl mediatekService;
    private final UserServiceImpl userService;
    private final BtcServiceImpl btcService;



    @GetMapping(value = "{id}")
    @ResponseBody
    public ResponseEntity<ApiResponse> getMovieList(
            @PathVariable UUID id,
            @RequestParam(name = "page", required = false) Integer page) {
        ApiResponse apiResponse = ApiResponse.builder().build();
        apiResponse.setTime(LocalDateTime.now());
        Optional<Mediatek> optMediatek = this.mediatekService.findById(id);
        List<MovieDto> movies = new ArrayList<>();
        if (optMediatek.isPresent()) {
            String l = optMediatek.get().getMovieIdList();
            if (!l.isEmpty()) {
                String[] ms = l.split(",");
                for (int i = 0; i < ms.length; i++) {
                    Long idLong = Long.parseLong(ms[i]);
                    Optional<Movie> optM = this.movieService.findMovieByMovieId(idLong);
                    if (optM.isPresent()) {
                        movies.add(MovieDto.fromMovie(optM.get()));
                    }
                }


                // pagination de la liste
                int defaultPage = 0;
                int defaultSize = 20;
                int currentPage = (page != null) ? page : defaultPage;

                Pageable pageable = PageRequest.of(currentPage, defaultSize);

                int start = (int) pageable.getOffset();
                int end = Math.min((start + pageable.getPageSize()), movies.size());
                int totalPages = (int) Math.ceil((double) movies.size() / defaultSize);

                Page<MovieDto> moviePage = new PageImpl<>(movies.subList(start, end));

                PaginateResponse paginateResponse = PaginateResponse.builder().build();
                paginateResponse.setPage(currentPage);
                paginateResponse.setResults(moviePage.getContent());
                paginateResponse.setTotal_pages(totalPages);
                paginateResponse.setTotal_results(movies.size());

                apiResponse.setData(paginateResponse);
            } else {
                apiResponse.setMessage("No movie into your mediatek.");
            }
            apiResponse.setStatus(EnumStatus.SUCCESS);

            return ResponseEntity.ok().body(apiResponse);
        } else {
            apiResponse.setStatus(EnumStatus.ERROR);
            apiResponse.setMessage("Mediatek was not found with id :" + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }


    }


    @PostMapping(path = "save")
    @ResponseBody
    public ResponseEntity<ApiResponse> saveMovie(@RequestBody MovieDto movie) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Movie m = Movie.fromMovieDto(movie);
        ApiResponse res = ApiResponse.builder().build();
        res.setTime(LocalDateTime.now());
        Optional<User> optU = userService.findByEmail(email);
        Optional<Movie> optMovie = this.movieService.findMovieByMovieId(movie.getId());

        if(optU.isEmpty()) {
            res.setStatus(EnumStatus.ERROR);
            res.setMessage("User not logged");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
        }else{
            User user = optU.get();
            // we check if movie exist already into the mediatek, if yes not necessary ti save the movie into database or mediatek
            Boolean ifMovieExistYetIntoMediatek = this.mediatekService.ifMediatekContain(m.getMovieId(),user.getId());

            if(!ifMovieExistYetIntoMediatek){
                // checking if exist into movie table
                if(optMovie.isEmpty()){
                    // we need just to add into the mediatek
                    try{
                        m.getGenres().forEach(this.genreService::save);
                        if (m.getBelongsToCollection() != null) btcService.save(m.getBelongsToCollection());
                        Movie movieSaved = movieService.save(m);
                    }catch(Exception e){
                        res.setStatus(EnumStatus.ERROR);
                        res.setMessage("Unable to save the movie into de datatable");
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
                    }
                }
                // we save the movie into the mediatek
                this.mediatekService.addMovie(m.getMovieId());
                res.setStatus(EnumStatus.SUCCESS);
                res.setMessage("Movie ' " + m.getTitle() + " ' saved successfully !");
                return ResponseEntity.ok().body(res);
            }else{
                res.setStatus(EnumStatus.ERROR);
                res.setMessage("Movie is already into your mediatek !");
                return ResponseEntity.ok().body(res);
            }
        }
    }

}
