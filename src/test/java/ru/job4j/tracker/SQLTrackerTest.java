package ru.job4j.tracker;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.tracker.input.Input;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class SQLTrackerTest {

   private static Connection connection;

    @BeforeClass
    public static void initConnection() {
        try (InputStream in = SqlTracker.class
                .getClassLoader().getResourceAsStream("test.properties")) {
            Properties properties = new Properties();
            properties.load(in);
            Class.forName(properties.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @After
    public void wipeTable() throws SQLException {
        try (PreparedStatement prState = connection.prepareStatement("delete from items")) {
            prState.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker sqlTracker = new SqlTracker(connection);
        Item item = new Item("Item");
        sqlTracker.add(item);
        assertThat(sqlTracker.findById(item.getId()), is(item));
    }

    @Test
    public void whenNeedFindItemAndReplaceByGeneratedId() {
        SqlTracker sqlTracker = new SqlTracker(connection);
        Item item1 = new Item("Item1");
        Item item2 = new Item("Item2");
        sqlTracker.add(item1);
        sqlTracker.add(item2);
        assertTrue(sqlTracker.replace(item1.getId(), item2));
    }

    @Test
    public void whenNeedFindIdAndDeleteHim() {
        SqlTracker sqlTracker = new SqlTracker(connection);
        Item item = new Item("Item1");
        sqlTracker.add(item);
        assertTrue(sqlTracker.delete(item.getId()));
    }

    @Test
    public void whenNeedFindAllItems() {
        SqlTracker sqlTracker = new SqlTracker(connection);
        Item item1 = new Item("Item1");
        Item item2 = new Item("Item2");
        Item item3 = new Item("Item3");
        sqlTracker.add(item1);
        sqlTracker.add(item2);
        sqlTracker.add(item3);
        assertThat(sqlTracker.findAll().size(), is(2));
    }

    @Test
    public void whenFindNameByGeneratedId() {
        SqlTracker sqlTracker = new SqlTracker(connection);
        Item item1 = new Item("Item1");
        sqlTracker.add(item1);
        assertThat(sqlTracker.findByName("Item1").get(0), is(item1));
    }
}
