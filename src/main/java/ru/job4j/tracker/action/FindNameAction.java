package ru.job4j.tracker.action;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.MemTracker;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;

import java.util.List;

public class FindNameAction implements UserAction {
    private final Output out;

    public FindNameAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Find Name Action";
    }

    @Override
    public boolean execute(Input input, MemTracker memTracker) {
        String name = input.askStr("Enter name");
        List<Item> items = memTracker.findByName(name);
        if (items.size() > 0) {
            for (Item item : items) {
                out.println(item.getName());
            }
        } else {
            out.println("Item with name not found");
        }
        return true;
    }
}
