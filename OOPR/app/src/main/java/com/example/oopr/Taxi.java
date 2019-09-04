package com.example.oopr;

public class Taxi extends Car {

    private int maxPassengers;

    public Taxi(String color, String bodyType, int doors, int speed) {
        super(color, bodyType, doors, speed);
    }

    public Taxi(String color, String bodyType, int doors, int speed, int maxPassengers) {
        super(color, bodyType, doors, speed);
        this.maxPassengers = maxPassengers;
    }
}
