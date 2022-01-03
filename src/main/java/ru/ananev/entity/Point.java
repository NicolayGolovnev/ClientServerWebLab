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
@Table(name = "point")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_point")
    private Long id;

    @Column(name = "location")
    private String pointLocation;

    @OneToMany(mappedBy = "pointDeparture", fetch = FetchType.LAZY)
    List<Order> departureOrders;

    @OneToMany(mappedBy = "pointArrival", fetch = FetchType.LAZY)
    List<Order> arrivalOrders = new ArrayList<>();;

    @OneToMany(mappedBy = "point", fetch = FetchType.LAZY)
    List<SequenceRoute> routes = new ArrayList<>();;
}
