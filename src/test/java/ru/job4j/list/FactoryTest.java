package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertThat;

public class FactoryTest {
    @Test
    public void main() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Factory.main(null);
        String ln = System.lineSeparator();
        String excepted = "first" + ln + "second"
                + ln + "third"
                + ln + "fourth"
                + ln + "fifth" + ln;
        assertThat(out.toString(), is(excepted));
    }

}