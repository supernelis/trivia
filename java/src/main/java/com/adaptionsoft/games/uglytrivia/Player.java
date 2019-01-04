package com.adaptionsoft.games.uglytrivia;

public class Player {
    private final String name;
    private int position = 0;
    private int coins = 0;
    private boolean inPenaltyBox = false;

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

    public void entersInPenaltyBox() {
        inPenaltyBox = true;
    }

    public void exitsPenaltyBox() {
        inPenaltyBox = false;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }

    @Override
    public String toString() {
        return name;
    }
}
