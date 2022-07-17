package ru.job4j.tracker.action;

import ru.job4j.tracker.storedb.MemTracker;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;

public class Exit implements UserAction {
    private final Output out;

    public Exit(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Exit";
    }

    @Override
    public boolean execute(Input input, MemTracker memTracker) {
        return false;
    }
}
