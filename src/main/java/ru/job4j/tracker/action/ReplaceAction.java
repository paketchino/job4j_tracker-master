package ru.job4j.tracker.action;

import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.storedb.MemTracker;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;

public class ReplaceAction implements UserAction {
    private final Output out;

    public ReplaceAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "=== Edit Items ===";
    }

    @Override
    public boolean execute(Input input, MemTracker memTracker) {
        int id = input.askInt("Enter id");
        String name = input.askStr("Enter name");
        Item newItem = new Item(name);
        boolean rsl = memTracker.replace(id, newItem);
        if (rsl) {
            out.println("Replace complete");
        } else {
            out.println("Not complete");
        }
        return true;
    }
}
