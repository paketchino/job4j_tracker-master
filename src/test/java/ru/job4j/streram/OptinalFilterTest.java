package ru.job4j.streram;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class OptinalFilterTest {

    @Test
    public void defineChildren() {
        OptionalFilter.Child c1 = new OptionalFilter.Child("Bob", 11);
        OptionalFilter.Child c2 = new OptionalFilter.Child("James", 12);
        OptionalFilter.Child c3 = new OptionalFilter.Child("Dick", 16);
        OptionalFilter.Child c4 = new OptionalFilter.Child("Arthur", 12);

        OptionalFilter.Worker w1 = new OptionalFilter.Worker("1", List.of(c1, c2));
        OptionalFilter.Worker w2 = new OptionalFilter.Worker("2", List.of(c3));
        OptionalFilter.Worker w3 = new OptionalFilter.Worker("3", List.of(c4));
        assertEquals(List.of(),
                OptionalFilter.defineChildren(List.of(w1, w2, w3), "1"));
    }
}