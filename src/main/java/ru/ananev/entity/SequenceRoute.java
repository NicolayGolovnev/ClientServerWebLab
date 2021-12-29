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
@Table(name = "sequence_route")
public class SequenceRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sequence")
    private Long id;

    @Column(name = "order_number")
    private int orderNumber;

    @Column(name = "arrival_date")
    private String arrivalDate;

    @Column(name = "dispatch_date")
    private String dispatchDate;

    @ManyToOne
    @JoinColumn(name = "id_point", nullable = false)
    private Point point;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_route", nullable = false)
    private Route route;
}
