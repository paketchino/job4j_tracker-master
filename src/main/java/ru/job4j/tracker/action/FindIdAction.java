package ru.job4j.tracker.action;

import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.storedb.MemTracker;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;

public class FindIdAction implements UserAction {
    private final Output out;

    public FindIdAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Find Id Action";
    }

    @Override
    public boolean execute(Input input, MemTracker memTracker) {
        int id = input.askInt("Enter id");
        Item item = memTracker.findById(id);
        if (item != null) {
            out.println(memTracker.findById(id));
        } else {
            out.println("Item with Id not found");
        }
        return true;
    }
}
