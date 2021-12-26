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
@Table(name = "Payment_note")
public class PaymentNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_note")
    private Long id;

    @Column(name = "payment")
    private int payment;

    @Column(name = "payment_date")
    private Date paymentDate;

    @ManyToOne
    @JoinColumn(name = "ID_order", nullable = false)
    Order order;
}
