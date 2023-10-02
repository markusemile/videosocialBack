package com.videosTek.backend.service.mediatek;

import com.videosTek.backend.entity.BelongsToCollection;
import com.videosTek.backend.entity.Mediatek;

import javax.print.attribute.standard.Media;
import java.util.Optional;
import java.util.UUID;

public interface MediatekService {

    Optional<Mediatek> findById(UUID id);

}
