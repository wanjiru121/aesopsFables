package com.example.oopr;

import android.util.Log;

public class Car {
    private String color;
    private String bodyType;
    private int doors;
    private int speed;

    public Car(String color, String bodyType, int doors, int speed) {
        this.color = color;
        this.bodyType = bodyType;
        this.doors = doors;
        this.speed = speed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void carryGoods(){
        Log.d("Car","The Car is carrying goods.");
    }
    public void hoot(){
        Log.d("Car","Ppiiii");
    }
    public int accelerate(int acceleration){
     speed = speed + acceleration;
     return speed;
    }
    public int decelerate(int deceleration){
        speed = speed - deceleration;
        return speed;
    }
    public void stop(){
        speed = 0;
    }
    public void hoot (String sound){
        Log.d("Car",sound);
    }

}
