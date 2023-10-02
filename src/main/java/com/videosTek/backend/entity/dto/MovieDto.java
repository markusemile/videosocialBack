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

    private String original_language;

    private String release_date;

    private String backdrop_path;

    private String poster_path;

    private List<Genre> genres = new ArrayList<>();

    private String overview;

    private String tagline;

    private Double popularity;

    private BelongsToCollection belongs_to_collection;

    //private ProductionCompany productionCompanies;

    // private ProductionCountry productionCountries;

    private Long budget;

    private Long revenue;

    private Integer voteCount;

    // private Credit credits;

    public static MovieDto fromMovie(Movie movie){
        return MovieDto.builder()
                .id(movie.getMovieId())
                .adult(movie.getAdult())
                .original_title(movie.getOriginalTitle())
                .title(movie.getTitle())
                .poster_path(movie.getPosterPath())
                .release_date(movie.getReleaseDate())
                .build();
    }

}
