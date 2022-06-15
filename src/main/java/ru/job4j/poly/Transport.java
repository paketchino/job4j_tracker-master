package ru.job4j.poly;

public class Transport implements StubTransport {

    @Override
    public void passengers(int passengers) {
        System.out.println("Passenger" + passengers + "set down in the car");
    }

    @Override
    public double fillTheBus(double fillTheBus) {
        int price = 1;
        return price * fillTheBus;
    }

    @Override
    public void drive() {
        System.out.println("The driver is driving on the road");
    }
}
