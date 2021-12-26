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
@Table(name = "Company_park")
public class CompanyPark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_park")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(targetEntity = Ship.class)
    @JoinColumn(name = "ID_park")
    List<Ship> ships;
}
