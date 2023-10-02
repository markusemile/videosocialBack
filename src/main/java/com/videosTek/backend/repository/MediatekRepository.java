package com.videosTek.backend.repository;

import com.videosTek.backend.entity.Mediatek;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.print.attribute.standard.Media;
import java.util.Optional;
import java.util.UUID;

public interface MediatekRepository extends JpaRepository<Mediatek, UUID> {

    Optional<Mediatek> findById(UUID id);


}
