package com.videosTek.backend.service.genre;

import com.videosTek.backend.entity.Genre;
import com.videosTek.backend.entity.Movie;
import com.videosTek.backend.repository.GenreRepository;
import com.videosTek.backend.repository.MovieRepository;
import com.videosTek.backend.service.genre.GenreService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public Optional<Genre> findGenreById(Integer id) {
        return genreRepository.findFirstById(id);
    }

    @Override
    public Optional<Genre> findGenreByName(String name) {
        return genreRepository.findFirstByName(name);
    }

    public void save(Genre genre){

        this.genreRepository.save(genre);
    }
}
