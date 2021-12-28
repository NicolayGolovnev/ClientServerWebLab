package ru.ananev.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "document_notes")
public class DocumentNotes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_note")
    private Long id;

    @Column(name = "unloading_mark")
    private Boolean unloadingMark;

    @OneToOne(targetEntity = Order.class)
    @JoinColumn(name = "id_order")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id_document", nullable = false)
    private TransportationDocument document;
}
