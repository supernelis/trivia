package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.Answer;
import com.adaptionsoft.games.trivia.runner.AnsweringService;
import com.adaptionsoft.games.trivia.runner.Dice;
import com.adaptionsoft.games.trivia.runner.GameRunner;
import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.Question;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static java.util.Optional.empty;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameRunnerUnitTest {

    private static final Question A_QUESTION = new Question("question");
    private static final Answer AN_ANSWER = new Answer(1);

    @Mock Game game;
    @Mock Dice dice;
    @Mock AnsweringService answeringService;
    private GameRunner gameRunner;

    @Before
    public void setUp() throws Exception {
        gameRunner = new GameRunner(game, dice, answeringService);
    }

    @Test
    public void requests_an_answer_only_when_a_question_is_asked() {
        when(answeringService.answer()).thenReturn(AN_ANSWER);
        when(dice.roll()).thenReturn(1).thenReturn(2);
        when(game.roll(1)).thenReturn(empty());
        when(game.roll(2)).thenReturn(Optional.of(A_QUESTION));

        gameRunner.run();

        verify(game, times(1)).handleAnswer(AN_ANSWER);
    }
}
