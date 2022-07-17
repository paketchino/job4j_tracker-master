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

            Item item1 = Item.of("Item1", "Desc", LocalDateTime.now());
            Item item2 = Item.of("Item2", "Desc2", LocalDateTime.now());
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
