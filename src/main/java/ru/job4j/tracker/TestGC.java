package ru.job4j.tracker;

import java.util.Random;

public class TestGC {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Item("N" + i);
        }
    }
}
