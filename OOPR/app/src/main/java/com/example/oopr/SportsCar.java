package com.example.oopr;

import android.util.Log;

public class SportsCar extends Car {
    public SportsCar(String color, String bodyType, int doors, int speed) {
        super(color, bodyType, doors, speed);
    }

    @Override
    public void hoot() {
//       super.hoot();
        Log.d("SportsCar","bbrrr");
    }
}
