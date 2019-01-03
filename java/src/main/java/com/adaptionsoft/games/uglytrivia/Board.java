package com.adaptionsoft.games.uglytrivia;

public class Board {
    private static final int NUMBER_OF_CELLS = 12;

    public Category categoryFor(int position) {
        Category[] categories = Category.values();
        return categories[position % categories.length];
    }

    public int nextPosition(int currentPosition, int roll) {
        return (currentPosition + roll) % NUMBER_OF_CELLS;
    }
}
