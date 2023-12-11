package com.example.springtest.models.enums;

public enum Role {
    USER(0), ADMIN(1);
    private final int i;

    Role(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }
    public String getRoleName() {
        return "ROLE_" + this.name();
    }
}
