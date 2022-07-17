package ru.job4j.tracker.action;

import org.junit.Test;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.storedb.MemTracker;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.StubOutput;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindNameActionTest {

    @Test
    public void whenFindActionByName() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = new Item("New Item 1");
        Item item2 = new Item("New Item 2");
        tracker.add(item);
        tracker.add(item2);
        String findByName = "Enter Name";
        FindNameAction findNameAction = new FindNameAction(out);
        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn(findByName);

        findNameAction.execute(input, tracker);

        assertThat(tracker.findAll().get(0).getName(), is(item.getName()));
    }

    @Test
    public void whenActionNotAddedThenReturnItemWithNameNotFound() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = new Item("New Item 1");

        FindNameAction findNameAction = new FindNameAction(out);
        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn("findByName");

        findNameAction.execute(input, tracker);

        String ln = System.lineSeparator();

        assertThat(out.toString(), is("Item with name not found" + ln));
        assertThat(tracker.findAll().size(), is(0));
    }

}