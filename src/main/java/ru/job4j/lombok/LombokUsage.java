package ru.job4j.lombok;

public class LombokUsage {

    public static void main(String[] args) {
        var bird = new Bird();
        bird.setAge(1);
        System.out.println(bird);
    }
}
