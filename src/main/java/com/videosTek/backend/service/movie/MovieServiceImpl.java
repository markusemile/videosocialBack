package com.videosTek.backend.service.movie;

import com.videosTek.backend.entity.Movie;
import com.videosTek.backend.repository.GenreRepository;
import com.videosTek.backend.repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;

    @Override
    public Optional<Movie> findMovieByMovieId(Long id) {
        return movieRepository.findByMovieId(id);
    }

    public Movie save(Movie m){
        Optional<Movie> opt = this.movieRepository.findByMovieId((m.getMovieId()));
        if(opt.isEmpty()) {
//            Movie mm = Movie.builder().build();
//            mm.setTitle("hello");
            this.movieRepository.save(m);
            return m;
        }else{
            return null;
        }
    }

}
