package com.videosTek.backend.repository;

import com.videosTek.backend.entity.Genre;
import com.videosTek.backend.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Integer> {

    Optional<Genre> findFirstByName(String name);
    Optional<Genre> findFirstById(Integer id);


}
