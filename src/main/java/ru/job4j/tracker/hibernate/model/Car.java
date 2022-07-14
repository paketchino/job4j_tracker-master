package ru.job4j.tracker.hibernate.model;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String model;

    private String owner;

    private Timestamp created;

    public Car(int id, String model, Timestamp created) {
        this.id = id;
        this.model = model;
        this.created = created;
    }

    public Car(String model, Timestamp created) {
        this.model = model;
        this.created = created;
    }

    public Car() {

    }

    public static Car of(String model, Timestamp created, String owner) {
        Car car = new Car();
        car.model = model;
        car.created = created;
        car.owner = owner;
        return car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id
                && Objects.equals(model, car.model)
                && Objects.equals(created, car.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, created);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
}
