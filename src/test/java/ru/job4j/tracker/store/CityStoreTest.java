package ru.job4j.tracker.store;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.tracker.model.City;

import java.util.List;

public class CityStoreTest {

    @Test
    public void whenCreate() {
        CityStore cityStore = new CityStore();
        City city = new City("Moscow");
        cityStore.create(city);
        List<City> all = cityStore.findAll();
        Assert.assertEquals(city, all.get(0));
    }

    @Test
    public void whenFindAll() {
        CityStore cityStore = new CityStore();
        City city1 = new City("Saratov");
        City city2 = new City("St. Petersburg");
        cityStore.create(city1);
        cityStore.create(city2);
        Assert.assertEquals(List.of(city1, city2), cityStore.findAll());
    }

}