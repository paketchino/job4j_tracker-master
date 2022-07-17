package ru.job4j.tracker.action;

import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.storedb.MemTracker;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;

import java.util.List;

public class FindAllAction implements UserAction {
    private final Output out;

    public FindAllAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Show All Action";
    }

    @Override
    public boolean execute(Input input, MemTracker memTracker) {
        List<Item> items = memTracker.findAll();
        for (Item item : items) {
            out.println(item.getName());
        }
        return true;
    }
}
