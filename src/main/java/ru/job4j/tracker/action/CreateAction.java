package ru.job4j.tracker.action;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.MemTracker;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;

public class CreateAction implements UserAction {
    private final Output out;

    public CreateAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return  "Create";
    }

    @Override
    public boolean execute(Input input, MemTracker memTracker) {
        out.println("=== Create a New Item ====");
        String name = input.askStr("Enter name");
        Item item = new Item(name);
        memTracker.add(item);
        return true;
    }
}
