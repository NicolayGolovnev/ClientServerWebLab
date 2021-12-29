package ru.ananev.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transportation_document")
public class TransportationDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_document")
    private long id;

    @Column(name = "number_document")
    private int docNumber;

    @ManyToOne()
    @JoinColumn(name = "id_ship", nullable = false)
    private Ship ship;

    @ManyToOne
    @JoinColumn(name = "id_route", nullable = false)
    private Route route;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DocumentNotes> notes = new ArrayList<>();

}
