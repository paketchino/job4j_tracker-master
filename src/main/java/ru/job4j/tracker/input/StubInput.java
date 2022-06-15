package ru.job4j.tracker.input;

import ru.job4j.tracker.input.Input;

public class StubInput implements Input {
    private String[] answer;
    private int position = 0;

    public StubInput(String[] answer) {
        this.answer = answer;
    }

    @Override
    public String askStr(String question) {
        return answer[position++];
    }

    @Override
    public int askInt(String question) {
        return Integer.parseInt(askStr(question));
    }

    @Override
    public int askInt(String question, int max) {
        int select = askInt(question, max);
        if (select < 0 || select > max) {
            throw new IllegalArgumentException();
        }
        return select;
    }
}
