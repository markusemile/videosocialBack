package com.videosTek.backend.service.genre;

import com.videosTek.backend.entity.Genre;
import com.videosTek.backend.entity.Movie;

import java.util.Optional;

public interface GenreService {

    Optional<Genre> findGenreById(Integer id);
    Optional<Genre> findGenreByName(String name);
}
