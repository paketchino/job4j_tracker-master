package ru.job4j.list;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class IteratorForListTest {
    @Test
    public void checkIterator() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        IteratorForList.main(null);
        String ln = System.lineSeparator();
        String excepted = "Next element has - true, this element is one" + ln
                + "Next element has - true, this element is two" + ln
                + "Next element has - true, this element is three" + ln
                + "Next element has - true, this element is four" + ln
                + "Next element has - true, this element is five" + ln
                + "Next element has - false" + ln;
        assertThat(out.toString(), is(excepted));
    }

}