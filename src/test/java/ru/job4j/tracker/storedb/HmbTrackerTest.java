package ru.job4j.tracker.storedb;


import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.tracker.model.Item;
import java.sql.Date;
import java.util.List;

public class HmbTrackerTest {

    @Ignore
    @Test
    public void whenAddItemThenReturnItem() {
        HmbTracker hmbTracker = new HmbTracker();
        Item item = new Item("Item1", "Desc", new Date(System.currentTimeMillis()));
        hmbTracker.add(item);
        List<Item> items = hmbTracker.findAll();
        for (Item i : items) {
            System.out.println(i);
        }
        Assert.assertEquals(item, items.get(0));
    }

    @Ignore
    @Test
    public void whenReplaceCreatedItemThenReturnReplaceItem() {
        HmbTracker hmbTracker = new HmbTracker();
        Item item1 = new Item("Item1", "Desc1", new Date(System.currentTimeMillis()));
        Item item2 = new Item("Item2", "Desc2", new Date(System.currentTimeMillis()));
        hmbTracker.add(item1);
        List<Item> items = hmbTracker.findAll();
        Assert.assertEquals(item1, items.get(0));
        hmbTracker.replace(item1.getId(), item2);
        List<Item> itemReplace = hmbTracker.findAll();
        Assert.assertEquals(item2.getName(), itemReplace.get(0).getName());
    }

    @Ignore
    @Test
    public void whenDeleteItemThenReturnNull() {
        HmbTracker hmbTracker = new HmbTracker();
        Item item1 = new Item("Item1", "Desc1", new Date(System.currentTimeMillis()));
        hmbTracker.add(item1);
        List<Item> items = hmbTracker.findAll();
        Assert.assertEquals(item1, items.get(0));
        hmbTracker.delete(item1.getId());
        List<Item> emptyList = hmbTracker.findAll();
        Assert.assertTrue(emptyList.isEmpty());
    }

    @Ignore
    @Test
    public void whenFindByNameThenReturnItemByName() {
        HmbTracker hmbTracker = new HmbTracker();
        Item item1 = new Item("Item1", "Desc1", new Date(System.currentTimeMillis()));
        hmbTracker.add(item1);
        List<Item> items = hmbTracker.findAll();
        Assert.assertEquals(item1, items.get(0));
        Assert.assertEquals(hmbTracker.findByName(item1.getName()), items);

    }

    @Ignore
    @Test
    public void whenFindByIdThenReturnItemById() {
        HmbTracker hmbTracker = new HmbTracker();
        Item item1 = new Item("Item1", "Desc1", new Date(System.currentTimeMillis()));
        hmbTracker.add(item1);
        List<Item> items = hmbTracker.findAll();
        Assert.assertEquals(item1, items.get(0));
        Assert.assertEquals(hmbTracker.findById(item1.getId()), items.get(0));
    }

}