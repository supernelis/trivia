package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;
import java.util.*;

public class Game {
    List<Player> players = new ArrayList<>();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    private final QuestionsDeck questionsDeck;
    private final Board board;

    public Game() {
        questionsDeck = new QuestionsDeck();
        board = new Board();
    }

    public void add(String playerName) {
        players.add(new Player(playerName));

        printer().println(playerName + " was added");
        printer().println("They are player number " + players.size());
    }

    public void roll(int roll) {
        Player currentPlayer = currentPlayer();
        printer().println(currentPlayer + " is the current player");
        printer().println("They have rolled a " + roll);

        if (currentPlayer.isInPenaltyBox()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;
                printer().println(currentPlayer + " is getting out of the penalty box");
            } else {
                printer().println(currentPlayer + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
                return;
            }
        }

        int newPosition = board.nextPosition(currentPlayer.position(), roll);
        currentPlayer.moveTo(newPosition);
        printer().println(currentPlayer + "'s new location is " + newPosition);


        Category category = board.categoryFor(newPosition);
        printer().println("The category is " + category);
        printer().println(questionsDeck.pickQuestionFor(category));
    }

    public boolean wasCorrectlyAnswered() {
        Player currentPlayer = currentPlayer();

        if (currentPlayer.isInPenaltyBox()) {
            if (!isGettingOutOfPenaltyBox) {
                this.currentPlayer++;
                if (this.currentPlayer == players.size()) this.currentPlayer = 0;
                return true;
            }
        }

        printer().println("Answer was correct!!!!");
        currentPlayer.reward();
        printer().println(currentPlayer + " now has " + currentPlayer.coins() + " Gold Coins.");

        this.currentPlayer++;
        if (this.currentPlayer == players.size()) this.currentPlayer = 0;

        return !currentPlayer.hasWon();
    }

    public boolean wrongAnswer() {
        printer().println("Question was incorrectly answered");
        printer().println(currentPlayer() + " was sent to the penalty box");
        currentPlayer().entersInPenaltyBox();

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return true;
    }

    protected PrintStream printer() {
        return System.out;
    }

    private Player currentPlayer() {
        return players.get(currentPlayer);
    }
}
