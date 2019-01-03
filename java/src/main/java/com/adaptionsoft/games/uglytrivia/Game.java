package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;
import java.util.*;

import static com.adaptionsoft.games.uglytrivia.Category.*;

public class Game {
    ArrayList players = new ArrayList();
    int[] places = new int[6];
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    private final QuestionsDeck questionsDeck;

    public Game() {
        questionsDeck = new QuestionsDeck();
    }

    public void add(String playerName) {
        players.add(playerName);
        places[howManyPlayers()] = 0;
        purses[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;

        printer().println(playerName + " was added");
        printer().println("They are player number " + players.size());
    }

    protected PrintStream printer() {
        return System.out;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        printer().println(players.get(currentPlayer) + " is the current player");
        printer().println("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                printer().println(players.get(currentPlayer) + " is getting out of the penalty box");
                places[currentPlayer] = currentPlayerPosition() + roll;
                if (currentPlayerPosition() > 11) places[currentPlayer] = currentPlayerPosition() - 12;

                printer().println(players.get(currentPlayer)
                        + "'s new location is "
                        + currentPlayerPosition());
                printer().println("The category is " + currentCategory().value());
                askQuestion();
            } else {
                printer().println(players.get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            places[currentPlayer] = currentPlayerPosition() + roll;
            if (currentPlayerPosition() > 11) places[currentPlayer] = currentPlayerPosition() - 12;

            printer().println(players.get(currentPlayer)
                    + "'s new location is "
                    + currentPlayerPosition());
            printer().println("The category is " + currentCategory().value());
            askQuestion();
        }

    }

    private void askQuestion() {
        String question = questionsDeck.pickQuestionFor(currentPlayerPosition());
        printer().println(question);
    }

    private Category currentCategory() {
        return categoryFor(currentPlayerPosition());
    }

    private int currentPlayerPosition() {
        return places[currentPlayer];
    }

    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                printer().println("Answer was correct!!!!");
                purses[currentPlayer]++;
                printer().println(players.get(currentPlayer)
                        + " now has "
                        + purses[currentPlayer]
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
            purses[currentPlayer]++;
            printer().println(players.get(currentPlayer)
                    + " now has "
                    + purses[currentPlayer]
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;

            return winner;
        }
    }

    public boolean wrongAnswer() {
        printer().println("Question was incorrectly answered");
        printer().println(players.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return true;
    }


    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == 6);
    }
}
