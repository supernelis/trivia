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

    protected PrintStream printer() {
        return System.out;
    }

    public void roll(int roll) {
        printer().println(currentPlayer() + " is the current player");
        printer().println("They have rolled a " + roll);

        if (isInPenaltyBox()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                printer().println(currentPlayer() + " is getting out of the penalty box");
                movePlayer(roll);

                printer().println(currentPlayer()
                        + "'s new location is "
                        + currentPlayerPosition());
                printer().println("The category is " + currentCategory());
                askQuestion();
            } else {
                printer().println(currentPlayer() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            movePlayer(roll);

            printer().println(currentPlayer()
                    + "'s new location is "
                    + currentPlayerPosition());
            printer().println("The category is " + currentCategory());
            askQuestion();
        }

    }

    private Player currentPlayer() {
        return players.get(currentPlayer);
    }

    private void movePlayer(int roll) {
        int newPosition = board.nextPosition(currentPlayerPosition(), roll);
        currentPlayer().moveTo(newPosition);
    }

    private void askQuestion() {
        String question = questionsDeck.pickQuestionFor(currentCategory());
        printer().println(question);
    }

    private Category currentCategory() {
        return board.categoryFor(currentPlayerPosition());
    }

    private int currentPlayerPosition() {
        return currentPlayer().position();
    }

    public boolean wasCorrectlyAnswered() {
        if (isInPenaltyBox()) {
            if (isGettingOutOfPenaltyBox) {
                printer().println("Answer was correct!!!!");
                currentPlayer().reward();
                printer().println(currentPlayer()
                        + " now has "
                        + currentPlayer().coins()
                        + " Gold Coins.");

                boolean winner = didPlayerWin();
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;

                return winner;
            } else {
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;
                return true;
            }


        } else {

            printer().println("Answer was corrent!!!!");
            currentPlayer().reward();
            printer().println(currentPlayer()
                    + " now has "
                    + currentPlayer().coins()
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;

            return winner;
        }
    }

    private boolean isInPenaltyBox() {
        return currentPlayer().isInPenaltyBox();
    }

    public boolean wrongAnswer() {
        printer().println("Question was incorrectly answered");
        printer().println(currentPlayer() + " was sent to the penalty box");
        currentPlayer().wrongAnswer();

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return true;
    }


    private boolean didPlayerWin() {
        return !currentPlayer().hasWon();
    }
}
