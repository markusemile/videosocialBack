package com.videosTek.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "belongs_to_collection")
public class BelongsToCollection {

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    private String posterPath;

    private String backdropPath;

    @JsonIgnore
    @OneToMany(mappedBy = "belongsToCollection")
    private List<Movie> movies=new ArrayList<>();

}
