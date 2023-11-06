package com.example.springtest.models.enums;

public enum Engine {
    GASOLINE(0), DIESEL(1), ELECTRIC(2), HYBRID(3);
    private final int i;

    Engine(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }
}
