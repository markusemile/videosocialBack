package com.videosTek.backend.service.btc;

import com.videosTek.backend.entity.BelongsToCollection;
import com.videosTek.backend.entity.Genre;
import com.videosTek.backend.repository.BTCRepository;
import com.videosTek.backend.service.btc.BtcService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class BtcServiceImpl implements BtcService {

    private final BTCRepository repository;

    @Override
    public Optional<BelongsToCollection> findGenreById(Long id) {
        return repository.findFirstById(id);
    }

    @Override
    public Optional<BelongsToCollection> findGenreByName(String name) {
        return Optional.empty();
    }

    public void save(BelongsToCollection btc){
        Optional<BelongsToCollection> opt = this.findGenreById(btc.getId());
        if(opt.isEmpty()) this.repository.save(btc);
    }
}
