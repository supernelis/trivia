package com.adaptionsoft.games.uglytrivia;

public class Board {
    private static final int NUMBER_OF_CELLS = 12;

    public int nextPosition(int currentPosition, int roll) {
        return (currentPosition + roll) % NUMBER_OF_CELLS;
    }
}
