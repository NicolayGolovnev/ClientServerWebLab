package ru.ananev.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_route")
    private Long id;

    @Column(name = "ship_requirement")
    private String shipRequirement;

    @OneToMany(mappedBy = "route", fetch = FetchType.LAZY)
    private List<TransportationDocument> documents;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SequenceRoute> sequenceRoutes = new ArrayList<>();
}
