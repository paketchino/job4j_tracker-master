package ru.job4j.tracker.storedb;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import ru.job4j.tracker.model.Item;

import java.time.LocalDateTime;
import java.util.List;

public class HmbTrackerTest {

    @Test
    public void whenAddItemThenReturnItem() {
        HmbTracker hmbTracker = new HmbTracker();
        Item item = Item.of("Item1", "Desc", LocalDateTime.now());
        hmbTracker.add(item);
        List<Item> items = hmbTracker.findAll();
        Assert.assertEquals(item, items.get(0));
    }

    @Test
    public void whenReplaceCreatedItemThenReturnReplaceItem() {
        HmbTracker hmbTracker = new HmbTracker();
        Item item1 = Item.of(1, "Item1", "Desc1", LocalDateTime.now());
        Item item2 = Item.of(item1.getId(), "Item2", "Desc2", LocalDateTime.now());
        hmbTracker.add(item1);
        List<Item> items = hmbTracker.findAll();
        Assert.assertEquals(item1, items.get(0));
        hmbTracker.replace(item1.getId(), item2);
        List<Item> itemReplace = hmbTracker.findAll();
        Assert.assertEquals(item2, itemReplace.get(0));
    }

    @Test
    public void whenDeleteItemThenReturnNull() {
        HmbTracker hmbTracker = new HmbTracker();
        Item item1 = Item.of(1, "Item1", "Desc1", LocalDateTime.now());
        hmbTracker.add(item1);
        List<Item> items = hmbTracker.findAll();
        Assert.assertEquals(item1, items.get(0));
        hmbTracker.delete(item1.getId());
        List<Item> emptyList = hmbTracker.findAll();
        Assert.assertTrue(emptyList.isEmpty());
    }

    @Test
    public void whenFindByNameThenReturnItemByName() {
        HmbTracker hmbTracker = new HmbTracker();
        Item item1 = Item.of(1, "Item1", "Desc1", LocalDateTime.now());
        hmbTracker.add(item1);
        List<Item> items = hmbTracker.findAll();
        Assert.assertEquals(item1, items.get(0));
        Assert.assertEquals(hmbTracker.findByName(item1.getName()), items);

    }

    @Test
    public void whenFindByIdThenReturnItemById() {
        HmbTracker hmbTracker = new HmbTracker();
        Item item1 = Item.of(1, "Item1", "Desc1", LocalDateTime.now());
        hmbTracker.add(item1);
        List<Item> items = hmbTracker.findAll();
        Assert.assertEquals(item1, items.get(0));
        Assert.assertEquals(hmbTracker.findById(item1.getId()), items.get(0));
    }

}