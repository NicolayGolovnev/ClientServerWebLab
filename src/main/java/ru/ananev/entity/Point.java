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
@Table(name = "point")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_point")
    private Long id;

    @Column(name = "location")
    private String pointLocation;

    @OneToMany(targetEntity = Order.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_point_departure")
    List<Order> departureOrders;

    @OneToMany(targetEntity = Order.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_point_arrival")
    List<Order> arrivalOrders;

    @OneToMany(targetEntity = SequenceRoute.class)
    @JoinColumn(name = "id_sequence")
    List<SequenceRoute> routes;
}
