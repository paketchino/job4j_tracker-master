package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

public class CheckAhdGetter {
    public static String getElement(List<String> list) {
        String result = "";
        if (!list.isEmpty()) {
            result = list.get(0);
        }
        return result;
    }
}
