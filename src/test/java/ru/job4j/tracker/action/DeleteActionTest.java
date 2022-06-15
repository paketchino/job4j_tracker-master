package ru.job4j.tracker.action;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.Test;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.MemTracker;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.StubOutput;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteActionTest  {

    @Test
    public void whenDeleteAddItemThenSuccessful() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("New Item"));
        String deleteItem = "Successful";
        DeleteAction deleteAction = new DeleteAction(out);
        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askStr(any(String.class))).thenReturn(deleteItem);

        deleteAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("Successful" + ln));
        assertThat(tracker.findAll().size(), is(0));
    }

    @Test
    public void whenDeleteEmptyListThenNotSuccessful() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        String deleteItem = "Not Successful";
        DeleteAction deleteAction = new DeleteAction(out);
        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askStr(any(String.class))).thenReturn(deleteItem);

        deleteAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("Not successful" + ln));
        assertThat(tracker.findAll().size(), is(0));
    }

}