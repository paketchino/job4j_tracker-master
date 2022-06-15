package ru.job4j.set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pangram {
    public static boolean checkString(String s) {
        boolean rsl = false;
        String[] strings = s.split("");

        Set<String> stringSet = new HashSet<>(Arrays.asList(strings));
        stringSet.remove(" ");
        if (stringSet.size() == 26) {
            rsl = true;
        }
        return rsl;
    }
}
