package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;

import static com.adaptionsoft.games.uglytrivia.Category.*;

public class Game {
    ArrayList players = new ArrayList();
    int[] places = new int[6];
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast(createRockQuestion(i));
        }
    }

    public String createRockQuestion(int index) {
        return "Rock Question " + index;
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
                places[currentPlayer] = currentPlayerPlace() + roll;
                if (currentPlayerPlace() > 11) places[currentPlayer] = currentPlayerPlace() - 12;

                printer().println(players.get(currentPlayer)
                        + "'s new location is "
                        + currentPlayerPlace());
                printer().println("The category is " + currentCategory().value());
                askQuestion();
            } else {
                printer().println(players.get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            places[currentPlayer] = currentPlayerPlace() + roll;
            if (currentPlayerPlace() > 11) places[currentPlayer] = currentPlayerPlace() - 12;

            printer().println(players.get(currentPlayer)
                    + "'s new location is "
                    + currentPlayerPlace());
            printer().println("The category is " + currentCategory().value());
            askQuestion();
        }

    }

    private void askQuestion() {
        if (currentCategory() == POP)
            printer().println(popQuestions.removeFirst());
        if (currentCategory() == SCIENCE)
            printer().println(scienceQuestions.removeFirst());
        if (currentCategory() == SPORTS)
            printer().println(sportsQuestions.removeFirst());
        if (currentCategory() == ROCK)
            printer().println(rockQuestions.removeFirst());
    }

    private Category currentCategory() {
        return categoryFor(currentPlayerPlace());
    }

    private int currentPlayerPlace() {
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
