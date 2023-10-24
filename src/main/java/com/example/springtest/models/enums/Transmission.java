package com.example.springtest.models.enums;

public enum Transmission {
    MANUAL(0), AUTOMATIC(1);
    private final int i;

    Transmission(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }
}
