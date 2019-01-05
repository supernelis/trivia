package com.adaptionsoft.games.uglytrivia;

import com.adaptionsoft.games.trivia.runner.Answer;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static java.util.Optional.empty;
import static org.junit.Assert.*;

public class GameTest {
    private static final Answer CORRECT_ANSWER = new SubbedAnswer(true);
    private static final Answer WRONG_ANSWER = new SubbedAnswer(false);

    private Player player;
    private Game game;

    @Before
    public void setUp() throws Exception {
        Console console = new DummyConsole();
        Players players = new Players();
        player = new Player("Matteo");
        players.addPlayer(player);
        game = new Game(console, players);
    }

    @Test
    public void player_enters_in_the_penalty_box_when_the_answer_is_wrong() {
        game.roll(3);
        game.handleAnswer(WRONG_ANSWER);

        assertTrue(player.isInPenaltyBox());
        assertEquals(3, player.position());
        assertEquals(0, player.coins());
    }

    @Test
    public void player_exits_the_penalty_box_when_roll_an_odd_number() {
        game.roll(2);
        game.handleAnswer(WRONG_ANSWER);

        game.roll(3);
        game.handleAnswer(CORRECT_ANSWER);

        assertFalse(player.isInPenaltyBox());
        assertEquals(5, player.position());
        assertEquals(1, player.coins());
    }

    @Test
    public void player_does_not_obtain_reward_when_she_is_in_the_penalty_box() {
        game.roll(1); // move 1
        game.handleAnswer(WRONG_ANSWER); // enters in the penalty box

        game.roll(2); // not move 2!!! she is in the penalty box
        game.handleAnswer(CORRECT_ANSWER); // no reward!!! she is in penalty box

        assertTrue(player.isInPenaltyBox());
        assertEquals(1, player.position());
        assertEquals(0, player.coins());
    }

    @Test
    public void player_does_not_get_a_question_when_she_is_in_the_penalty_box() {
        game.roll(1); // move 1
        game.handleAnswer(WRONG_ANSWER); // enters in the penalty box

        Optional<Question> question = game.roll(2); // not move 2!!! she is in the penalty box

        assertEquals(empty(), question);
    }

    @Test
    public void player_get_a_question_when_she_is_exiting_the_penalty_box() {
        game.roll(1);
        game.handleAnswer(WRONG_ANSWER);

        Optional<Question> question = game.roll(1);

        assertTrue(question.isPresent());
    }

    @Test
    public void player_obtains_rewards_when_she_exits_the_penalty_box() {
        game.roll(1); // move 1
        game.handleAnswer(WRONG_ANSWER); // enters in the penalty box

        game.roll(1); // moves 1!!! exits the penalty box
        game.handleAnswer(CORRECT_ANSWER); //reward!!! exiting penalty box

        game.roll(2); // move 2!!! not in the penalty box
        game.handleAnswer(CORRECT_ANSWER); // reward!!! not in penalty box

        assertFalse(player.isInPenaltyBox());
        assertEquals(4, player.position());
        assertEquals(2, player.coins());
    }

    private static class SubbedAnswer extends Answer {
        private final boolean correct;

        SubbedAnswer(boolean correct) {
            super(-1);
            this.correct = correct;
        }

        @Override
        public boolean isCorrect() {
            return correct;
        }
    }

    private class DummyConsole extends Console {
        @Override
        public void print(String message) {}
    }
}