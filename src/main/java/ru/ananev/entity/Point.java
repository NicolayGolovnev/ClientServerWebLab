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
@Table(name = "Point")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_point")
    private Long id;

    @Column(name = "location")
    private String location;

    @OneToMany(targetEntity = Order.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_point_departure")
    List<Order> departureOrders;

    @OneToMany(targetEntity = Order.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_point_arrival")
    List<Order> arrivalOrders;

    @OneToMany(targetEntity = SequenceRoute.class)
    @JoinColumn(name = "ID_sequence")
    List<SequenceRoute> routes;
}
