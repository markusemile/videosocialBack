package com.videosTek.backend.entity;

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

    @ManyToMany()
    private List<Genre> genres;

    @Column(columnDefinition = "TEXT")
    private String overview;

    private String tagline;

    private Double popularity;

    @ManyToOne()
    @JoinColumn(referencedColumnName = "id")
    private BelongsToCollection belongsToCollection;

    //private ProductionCompany productionCompanies;

   // private ProductionCountry productionCountries;

    private Long budget;

    private Long revenue;

    private Integer voteCount;

   // private Credit credits;

    public static Movie fromMovieDto(MovieDto movieDto){
        return Movie.builder()
                .movieId(movieDto.getId())
                .title(movieDto.getTitle())
                .originalTitle(movieDto.getOriginal_title())
                .releaseDate(movieDto.getRelease_date())
                .overview(movieDto.getOverview())
                .tagline(movieDto.getTagline())
                .belongsToCollection(movieDto.getBelongs_to_collection())
                .adult(movieDto.getAdult())
                .backdropPath(movieDto.getBackdrop_path())
                .posterPath(movieDto.getPoster_path())
                .genres(movieDto.getGenres())
                .originalLanguage(movieDto.getOriginal_language())
                .budget(movieDto.getBudget())
                .revenue(movieDto.getRevenue())
                .popularity(movieDto.getPopularity())
                .voteCount(movieDto.getVoteCount())
                .build();
    }

}
