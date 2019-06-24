package com.adaptionsoft.games.uglytrivia;

public enum Category {
    POP("Pop"), SCIENCE("Science"), SPORTS("Sports"), ROCK("Rock");
    private String value;

    public String toString() {
        return value;
    }

    Category(String value) {
        this.value = value;
    }
}