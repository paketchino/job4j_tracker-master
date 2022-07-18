package ru.job4j.tracker.storedb;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.store.Store;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class HmbTracker implements Store, AutoCloseable {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public void init() {

    }

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        HmbTracker tracker = new HmbTracker();
        tracker.close();
        return null;
    }

    @Override
    public boolean replace(int id, Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery(
                "update Item i set i.name = :newName, "
                        + "i.description = :newDesc, i.created = :newTimestump where i.id = :fId"
        );
        query.setParameter("newName", "Item99");
        query.setParameter("newDesc", "desc3");
        query.setParameter("newTimestump", Timestamp.valueOf(LocalDateTime.now()));
        query.setParameter("fId", id);
        boolean rsl = query.executeUpdate() > 0;
        session.getTransaction().commit();
        HmbTracker tracker = new HmbTracker();
        tracker.close();
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from Item where id =: fId");
        query.setParameter("fId", id);
        boolean condition = query.executeUpdate() > 0;
        session.getTransaction().commit();
        HmbTracker tracker = new HmbTracker();
        tracker.close();
        return condition;
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List itemList = session.createQuery("from Item").list();
        session.getTransaction().commit();
        HmbTracker hmbTracker = new HmbTracker();
        hmbTracker.close();
        return itemList;
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Item i where i.name =: fName");
        query.setParameter("fName", key);
        List list = query.list();
        System.out.println(query);
        session.getTransaction().commit();
        session.close();
        return list;
    }

    @Override
    public Item findById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Item i where i.id =: fId");
        query.setParameter("fId", id);
        Item item = session.get(Item.class, id);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
