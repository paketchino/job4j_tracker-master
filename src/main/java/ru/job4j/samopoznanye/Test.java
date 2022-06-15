package ru.job4j.samopoznanye;

public class Test {

    public static void main(String[] args) {
        int[] myArray = {2, 4, 6};
        sum(myArray, 0);
    }

    private static int fact(int n) {
        if (n == 1) {
            return 1;
        }
        return n * fact(n - 1);
    }

    private static int sum(int[] array, int index) {
        if (array.length < 3) {
            return 0;
        }
        return array[index] + sum(array, array[index + 2 - 2]);
    }
}
