package ru.ananev.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Document_notes")
public class DocumentNotes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_note")
    private Long id;

    @Column(name = "unloading_mark")
    private Boolean unloadingMark;

    @OneToOne(targetEntity = Order.class)
    @JoinColumn(name = "ID_order")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "ID_document", nullable = false)
    private TransportationDocument document;
}
