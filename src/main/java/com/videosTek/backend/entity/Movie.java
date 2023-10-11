package com.videosTek.backend.entity;

import com.videosTek.backend.entity.dto.MovieDetailDto;
import com.videosTek.backend.entity.dto.MovieDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private Long movieId;

    private String title;

    private String originalTitle;

    private Boolean adult;

    private String originalLanguage;

    private String releaseDate;

    @Column(unique = true)
    private String backdropPath;

    @Column(unique = true)
    private String posterPath;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Genre> genres;

    @Column(columnDefinition = "TEXT")
    private String overview;

    private String tagline;

    private Double popularity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id")
    private BelongsToCollection belongsToCollection;

    //private ProductionCompany productionCompanies;

   // private ProductionCountry productionCountries;

    private Long budget;

    private Long revenue;

    private Integer voteCount;

    private Integer runtime;

   // private Credit credits;

    public static Movie fromMovieDto(MovieDetailDto movieDto){
        return Movie.builder()
                .adult(movieDto.getAdult())
                .backdropPath(movieDto.getBackdrop_path())
                .budget(movieDto.getBudget())
                .genres(movieDto.getGenres())
                .movieId(movieDto.getId())
                .originalLanguage(movieDto.getOriginal_language())
                .originalTitle(movieDto.getOriginal_title())
                .overview(movieDto.getOverview())
                .popularity(movieDto.getPopularity())
                .posterPath(movieDto.getPoster_path())
                .releaseDate(movieDto.getRelease_date())
                .revenue(movieDto.getRevenue())
                .tagline(movieDto.getTagline())
                .title(movieDto.getTitle())
                .voteCount(movieDto.getVote_count())
                .belongsToCollection(movieDto.getBelongs_to_collection())
                .runtime(movieDto.getRuntime())
                .build();
    }

}
