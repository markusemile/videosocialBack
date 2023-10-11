package com.videosTek.backend.entity.dto;

import com.videosTek.backend.entity.BelongsToCollection;
import com.videosTek.backend.entity.Genre;
import com.videosTek.backend.entity.Movie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {

    private Long id;

    private String title;

    private String original_title;

    private Boolean adult;

    private String release_date;

    private String poster_path;

    private String tagline;





    public static MovieDto fromMovie(Movie movie){
        return MovieDto.builder()
                .id(movie.getMovieId())
                .adult(movie.getAdult())
                .original_title(movie.getOriginalTitle())
                .title(movie.getTitle())
                .tagline(movie.getTagline())
                .poster_path(movie.getPosterPath())
                .release_date(movie.getReleaseDate())
                .build();
    }

}
