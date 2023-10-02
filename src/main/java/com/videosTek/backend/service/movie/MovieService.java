package com.videosTek.backend.service.movie;

import com.videosTek.backend.entity.Movie;

import java.util.Optional;

public interface MovieService {

    Optional<Movie> findMovieByMovieId(Long id);
}
