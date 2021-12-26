package ru.ananev.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_route")
    private Long id;

    @Column(name = "ship_requirement")
    private String shipRequirement;

    @OneToMany(targetEntity = TransportationDocument.class)
    @JoinColumn(name = "ID_route")
    private List<TransportationDocument> documents;

    @OneToMany(targetEntity = SequenceRoute.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ID_route")
    private List<SequenceRoute> sequenceRoutes;
}
