package ru.job4j.tracker.storedb;

import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.store.Store;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public final class MemTracker implements Store {

    private static MemTracker instance = null;

    private Connection cn;

    private final List<Item> items = new ArrayList<>();
    private int ids = 1;

    public MemTracker() {
    }

    public static MemTracker getInstance() {
        if (instance == null) {
            instance = new MemTracker();
        }
        return instance;    
    }

    @Override
    public void init() {
        try (InputStream io = MemTracker.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(io);
            Class.forName("org.postgresql.Driver");
            cn = DriverManager.getConnection(
              config.getProperty("url"),
              config.getProperty("username"),
              config.getProperty("password")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    public Item add(Item item) {
        /**
         * добавляет заявку в массив items
         * через setId проставляется ключ
         * ids используется для регенерации нового ключа
         */
        item.setId(ids++);
        items.add(item);
        return item;
    }

    /**
     * size - поле массива
     *
     * @return
     */
    public List<Item> findAll() {
        return items;
    }

    /**
     * Создать результирующий массив где будут храниться ячейки
     * newSize = счетчик который будет перемещаться по результирующему массиву
     *
     * @param key
     * @return
     */
    public List<Item> findByName(String key) {
        List<Item> rsl = new ArrayList<>();
       for (Item item : items) {
            if (key.equals(item.getName())) {
                rsl.add(item);
            }
        }
        return rsl;
    }

    public Item findById(int id) {
        /* Находим индекс */
        int index = indexOf(id);
        /* Если индекс найден возвращаем item, иначе null */
        return index != -1 ? items.get(index) : null;
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < items.size(); index++) {
            if (items.get(index).getId() == id) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    /**
     * @param id   - уникальный идентификатор заявки
     * @param item - заявку которую нужно заменить
     * @return возвращает заявку
     */

    public boolean replace(int id, Item item) {
        int index = indexOf(id);
        boolean rsl = index != 0;
        if (rsl) {
            items.set(id, item);
        }
        return rsl;
    }

    /**
     * Данный метод удаляет id выбранного элемента
     * @param id = индекс элемента который будет удален
     * @return возвращает true если удален, в противном случае false
     */

    @Override
    public boolean delete(int id) {
        boolean result = false;
        int index = indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
            items.remove(index);
        }
        return result;
    }

    /**
     *
     * int start - индекс следующего элемента
     * @param id
     * @return
     */
    public boolean deleted(int id) {
        int index = indexOf(id);
        boolean rsl = index != -1;
        if (rsl) {
            items.remove(index);
        }
        return rsl;
    }

}
