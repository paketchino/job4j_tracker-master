package ru.job4j.tracker.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "name")
    private String name;

    public City(String name) {
        this.name = name;
    }

}
