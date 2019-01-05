package com.adaptionsoft.games.trivia.runner;

public class Answer {
    private static final int INCORRECT_ANSWER = 7;
    private final int answer;

    public Answer(int answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return answer != INCORRECT_ANSWER;
    }
}
