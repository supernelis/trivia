package com.adaptionsoft.games.uglytrivia;

public class Player {
    private final String name;
    private int position = 0;
    private int coins = 0;

    public Player(String name) {
        this.name = name;
    }

    public void moveTo(int newPosition) {
        this.position = newPosition;
    }

    public int position() {
        return position;
    }

    public void reward() {
        coins++;
    }

    public int coins() {
        return coins;
    }

    public boolean hasWon() {
        return coins == 6;
    }

    @Override
    public String toString() {
        return name;
    }
}
