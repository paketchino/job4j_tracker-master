package ru.job4j.tracker.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.hibernate.model.Car;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.sql.Date;

public class HblRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            new Date(System.currentTimeMillis());
            Item item1 = new Item(1, "Item1", "Desc", new Date(System.currentTimeMillis()));
            Item item2 = new Item(2, "Item2", "Desc2", new Date(System.currentTimeMillis()));
            Car car = Car.of("Toyota", new Timestamp(1459510232000L), "Sergey");
            session.save(car);
            session.save(item1);
            session.save(item2);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
