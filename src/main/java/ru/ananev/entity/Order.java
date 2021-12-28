package ru.ananev.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "COrder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_order")
    private Long id;

    @Column(name = "departure_date")
    private Date departureDate;

    @Column(name = "arrival_date")
    private Date arrivalDate;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "cargo_weight")
    private int cargoWeight;

    @Column(name = "cost_delivery")
    private int costDelivery;

    @ManyToOne
    @JoinColumn(name = "ID_customer", nullable = false)
    Customer customer;

    @ManyToOne
    @JoinColumn(name = "ID_point_departure", nullable = false)
    Point pointDeparture;

    @ManyToOne
    @JoinColumn(name = "ID_point_arrival", nullable = false)
    Point pointArrival;

    @OneToMany(targetEntity = PaymentNote.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ID_order")
    List<PaymentNote> paymentNotes;

    @OneToOne(targetEntity = DocumentNotes.class)
    @JoinColumn(name = "ID_order")
    private DocumentNotes documentNote;
}
