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
@Table(name = "Ship")
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ship")
    private Long id;

    @Column(name = "lifting_capacity")
    private int liftingCapacity;

    @Column(name = "passability")
    private String passability;

    @Column(name = "price")
    private int price;

    @Column(name = "state")
    private String state;

    @OneToMany(targetEntity = TransportationDocument.class)
    @JoinColumn(name = "ID_ship")
    private List<TransportationDocument> documents;

    @ManyToOne()
    @JoinColumn(name = "ID_park", nullable = false)
    private CompanyPark park;
}
