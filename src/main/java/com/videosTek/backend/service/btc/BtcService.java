package com.videosTek.backend.service.btc;

import com.videosTek.backend.entity.BelongsToCollection;
import com.videosTek.backend.entity.Genre;

import java.util.Optional;

public interface BtcService {

    Optional<BelongsToCollection> findGenreById(Long id);
    Optional<BelongsToCollection> findGenreByName(String name);
}
