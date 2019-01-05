package com.adaptionsoft.games.trivia.runner;

import java.util.Random;

public class AnsweringService {
    private Random random;

    public AnsweringService(Random random) {
        this.random = random;
    }

    public Answer answer() {
        int answer = random.nextInt(9);
        return new Answer(answer);
    }
 }
