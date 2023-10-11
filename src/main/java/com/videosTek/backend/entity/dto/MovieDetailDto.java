package com.videosTek.backend.entity.dto;

import com.videosTek.backend.entity.BelongsToCollection;
import com.videosTek.backend.entity.Genre;
import com.videosTek.backend.entity.Movie;
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
public class MovieDetailDto {

    private Long id;

    private Boolean adult;

    private String backdrop_path;

    private BelongsToCollection belongs_to_collection;

    private Long budget;

    private List<Genre> genres = new ArrayList<>();

    private String original_language;

    private String original_title;

    private String overview;

    private Double popularity;

    private String poster_path;

    private String release_date;

    private Long revenue;

    private Integer runtime;

    private String tagline;

    private String title;

    private Integer vote_count;

    //private ProductionCompany productionCompanies;

    // private ProductionCountry productionCountries;

    // private Credit credits;

    public static MovieDetailDto fromMovie(Movie movie){
        return MovieDetailDto.builder()
                .adult(movie.getAdult())
                .backdrop_path(movie.getBackdropPath())
                .belongs_to_collection(movie.getBelongsToCollection())
                .budget(movie.getBudget())
                .genres(movie.getGenres())
                .id(movie.getId())
                .original_language(movie.getOriginalLanguage())
                .original_title(movie.getOriginalTitle())
                .overview(movie.getOverview())
                .popularity(movie.getPopularity())
                .poster_path(movie.getPosterPath())
                .release_date(movie.getReleaseDate())
                .revenue(movie.getRevenue())
                .tagline(movie.getTagline())
                .title(movie.getTitle())
                .vote_count(movie.getVoteCount())
                .runtime(movie.getRuntime())
                .build();
    }
}
