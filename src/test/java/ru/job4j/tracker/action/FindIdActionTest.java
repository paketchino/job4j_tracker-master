package ru.job4j.tracker.action;

import junit.framework.TestCase;
import org.junit.Test;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.MemTracker;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.StubOutput;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class FindIdActionTest {

    @Test
    public void whenFindByIdAction() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = new Item("New Item");
        tracker.add(item);
        String findById = "Find Action";
        FindIdAction findIdAction = new FindIdAction(out);
        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askStr(any(String.class))).thenReturn(findById);

        findIdAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is(item + ln));
        assertThat(tracker.findById(1).getId(), is(item.getId()));

    }

}