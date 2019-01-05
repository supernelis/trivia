package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.trivia.runner.Answer;

import java.util.Optional;

import static java.util.Optional.empty;

public class Game {
    public static final boolean GAME_ON_GOING = true;

    private final QuestionsDeck questionsDeck = new QuestionsDeck();
    private final Board board = new Board();
    private final Players players;
    private final Console console;

    public Game(Console console, Players players) {
        this.console = console;
        this.players = players;
    }

    public Optional<Question> roll(int roll) {
        Player currentPlayer = players.currentPlayer();
        console.print(currentPlayer + " is the current player");
        console.print("They have rolled a " + roll);

        if (currentPlayer.isInPenaltyBox()) {
            if (isGettingOutOfPenaltyBox(roll)) {
                currentPlayer.exitsPenaltyBox();
                console.print(currentPlayer + " is getting out of the penalty box");
            } else {
                console.print(currentPlayer + " is not getting out of the penalty box");
                players.nextPlayer();
                return empty();
            }
        }

        int newPosition = board.nextPosition(currentPlayer.position(), roll);
        currentPlayer.moveTo(newPosition);
        console.print(currentPlayer + "'s new location is " + newPosition);

        Category category = board.categoryFor(newPosition);
        console.print("The category is " + category);

        Question question = questionsDeck.pickQuestionFor(category);
        console.print(question.toString());
        return Optional.of(question);
    }

    public boolean handleAnswer(Answer answer) {
        Player currentPlayer = players.currentPlayer();
        players.nextPlayer();

        if (currentPlayer.isInPenaltyBox()) {
            return GAME_ON_GOING;
        }

        if (answer.isCorrect()) {
            console.print("Answer was correct!!!!");
            currentPlayer.reward();
            console.print(currentPlayer + " now has " + currentPlayer.coins() + " Gold Coins.");

            return !currentPlayer.hasWon();
        }

        console.print("Question was incorrectly answered");
        console.print(currentPlayer + " was sent to the penalty box");

        currentPlayer.entersInPenaltyBox();

        return GAME_ON_GOING;
    }

    private boolean isGettingOutOfPenaltyBox(int roll) {
        return roll % 2 != 0;
    }
}
