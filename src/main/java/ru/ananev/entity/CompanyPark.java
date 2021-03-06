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
@Table(name = "company_park")
public class CompanyPark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_park")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "park", orphanRemoval = true, cascade = CascadeType.ALL)
    List<Ship> ships;
}
