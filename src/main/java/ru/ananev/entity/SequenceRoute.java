package ru.ananev.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Sequence_route")
public class SequenceRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_sequence")
    private Long id;

    @Column(name = "order_number")
    private int orderNumber;

    @Column(name = "arrival_date")
    private Date arrivalDate;

    @Column(name = "dispatch_date")
    private Date dispatchDate;

    @ManyToOne
    @JoinColumn(name = "ID_point", nullable = false)
    private Point point;

    @ManyToOne
    @JoinColumn(name = "ID_route", nullable = false)
    private Route route;
}
