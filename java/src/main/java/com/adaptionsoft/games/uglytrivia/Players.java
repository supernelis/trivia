package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private static final int MAXIMUM_NUMBER_OF_PLAYERS = 6;

    private List<Player> players = new ArrayList<>();
    private int currentPlayerIndex = 0;

    public void addPlayer(Player player) {
        if (numberOfPlayers() == MAXIMUM_NUMBER_OF_PLAYERS) {
            throw new MaximumNumberOfPlayersException();
        }

        players.add(player);
    }

    public void nextPlayer() {
        if (numberOfPlayers() == 0) {
            throw new NoPlayersException();
        }

        currentPlayerIndex = (currentPlayerIndex + 1) % numberOfPlayers();
    }

    public Player currentPlayer() {
        if (numberOfPlayers() == 0) {
            throw new NoPlayersException();
        }

        return players.get(currentPlayerIndex);
    }

    public int numberOfPlayers() {
        return players.size();
    }
}
