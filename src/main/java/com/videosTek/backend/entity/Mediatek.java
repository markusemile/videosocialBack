package com.videosTek.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name="`mediatek`")
public class Mediatek {

    @Id
    @Column(unique = true)
    private UUID id;

    @Column(columnDefinition = "TEXT")
    private String movieIdList;

}
