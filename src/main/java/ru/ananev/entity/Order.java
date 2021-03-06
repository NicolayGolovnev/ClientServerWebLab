package ru.ananev.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "corder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long id;

    @Column(name = "departure_date")
    private String departureDate;

    @Column(name = "arrival_date")
    private String arrivalDate;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "cargo_weight")
    private int cargoWeight;

    @Column(name = "cost_delivery")
    private int costDelivery;

    @ManyToOne
    @JoinColumn(name = "id_customer", nullable = false)
    Customer customer;

    @ManyToOne
    @JoinColumn(name = "id_point_departure", nullable = false)
    Point pointDeparture;

    @ManyToOne
    @JoinColumn(name = "id_point_arrival", nullable = false)
    Point pointArrival;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    List<PaymentNote> paymentNotes = new ArrayList<>();

    @OneToOne(mappedBy = "order")
    private DocumentNotes documentNote;
}
