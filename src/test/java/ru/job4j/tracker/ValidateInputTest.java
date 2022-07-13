package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.input.StubInput;
import ru.job4j.tracker.input.ValidateInput;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.StubOutput;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class ValidateInputTest {
    @Test
    public void whenInvalidInput() {
        Output out = new StubOutput();
        String[] in = new String[] {"one", "1"};
        ValidateInput input = new ValidateInput(new StubInput(in));
        int selected = input.askInt("Enter menu");
        assertThat(selected, is(1));
    }

    @Test
    public void whenInvalidMultiple() {
        Output output = new StubOutput();
        String[] data = new String[] {"1", "1", "1"};
        ValidateInput input = new ValidateInput(new StubInput(data));
        int selected = input.askInt("Enter menu");
        assertThat(selected, is(1));
    }

    @Test
    public void whenInvalidNegative() {
        Output output = new StubOutput();
        String[] data = new String[] {"-1"};
        ValidateInput input = new ValidateInput(new StubInput(data));
        int selected = input.askInt("Enter menu :");
        assertThat(selected, is(-1));
    }
}