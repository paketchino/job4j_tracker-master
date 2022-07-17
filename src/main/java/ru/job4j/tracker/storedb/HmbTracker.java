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
        Query query = session.createQuery("insert into Item (name, description, created) "
                        + "select concat(i.name, ' NEW'), i.description, i.created "
                        + "from Item i where i.id = :fId");
                query.setParameter("fId", 1)
                .executeUpdate();
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
        session.getTransaction().commit();
        HmbTracker tracker = new HmbTracker();
        tracker.close();
        return false;
    }

    @Override
    public boolean delete(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from Item where id =: fId");
        query.setParameter("fId", id);
        boolean condition = query != null;
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
        System.out.println(query.uniqueResult());
        session.getTransaction().commit();
        session.close();
        return list;
    }

    @Override
    public Item findById(int id) {
        Item item = null;
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Item i where i.id =: fId");
        query.setParameter("fId", id);
        List<Item> itemList = query.list();
        for (Item i : itemList) {
            item = i;
        }
        session.getTransaction().commit();
        session.close();
        return item;
    }

    @Override
    public void close() {
        sf.close();
    }
}
