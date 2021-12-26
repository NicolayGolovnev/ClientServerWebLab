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
@Table(name = "Transportation_document")
public class TransportationDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_document")
    private long id;

    @Column(name = "number_document")
    private int docNumber;

    @ManyToOne()
    @JoinColumn(name = "ID_ship", nullable = false)
    private Ship ship;

    @ManyToOne
    @JoinColumn(name = "ID_route", nullable = false)
    private Route route;

    @OneToMany(targetEntity = DocumentNotes.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ID_document")
    private List<DocumentNotes> notes;

}
