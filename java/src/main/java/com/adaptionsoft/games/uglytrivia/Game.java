package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;

public class Game {
    boolean isGettingOutOfPenaltyBox;

    private final QuestionsDeck questionsDeck = new QuestionsDeck();
    private final Board board = new Board();
    private final Players players = new Players();

    public void add(String playerName) {
        players.addPlayer(new Player(playerName));

        printer().println(playerName + " was added");
        printer().println("They are player number " + players.numberOfPlayers());
    }

    public void roll(int roll) {
        Player currentPlayer = players.currentPlayer();
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
        Player currentPlayer = players.currentPlayer();
        players.nextPlayer();

        if (currentPlayer.isInPenaltyBox()) {
            if (!isGettingOutOfPenaltyBox) {
                return true;
            }
        }

        printer().println("Answer was correct!!!!");
        currentPlayer.reward();
        printer().println(currentPlayer + " now has " + currentPlayer.coins() + " Gold Coins.");

        return !currentPlayer.hasWon();
    }

    public boolean wrongAnswer() {
        printer().println("Question was incorrectly answered");
        printer().println(players.currentPlayer() + " was sent to the penalty box");
        players.currentPlayer().entersInPenaltyBox();

        players.nextPlayer();
        return true;
    }

    protected PrintStream printer() {
        return System.out;
    }
}
