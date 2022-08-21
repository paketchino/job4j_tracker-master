package ru.job4j.tracker.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.sql.Date;

@NoArgsConstructor
@Data
@Entity
@Table(name = "items")
public class Item implements Comparator<Item>   {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "name")
    private String name;
    @JoinColumn(name = "description")
    private String description;

    @Getter
    @Setter
    @Column(name = "created", columnDefinition = "DATE")
    private Date date = new Date(System.currentTimeMillis());

    public Item(String name) {
        this.name = name;
    }

    public Item(String name, String description, Date date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public Item(int id, String name, String description, Date date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
    }

    @Override
    public int compare(Item o, Item j) {
        return o.getName().compareTo(j.getName());
    }
}
