package com.adaptionsoft.games.uglytrivia;

public class Player {
    private final String name;
    private int position;

    public Player(String name) {
        this.name = name;
        this.position = 0;
    }

    public void moveTo(int newPosition) {
        this.position = newPosition;
    }

    public int position() {
        return position;
    }

    @Override
    public String toString() {
        return name;
    }
}
