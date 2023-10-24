package com.example.springtest.models.enums;

public enum Category {
    CAR(0), BUSS(1), TRUCK(2), MOTORCYCLE(3);
    private final int i;

    Category(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }
}
