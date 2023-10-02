package com.videosTek.backend.repository;

import com.videosTek.backend.entity.BelongsToCollection;
import com.videosTek.backend.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BTCRepository extends JpaRepository<BelongsToCollection,Long> {

    Optional<BelongsToCollection> findFirstByName(String name);
    Optional<BelongsToCollection> findFirstById(Long id);

}
