package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.junit.Test;

import java.util.Random;

public class GameRunnerTest {

	@Test
	public void true_is_true() throws Exception {
		GameRunner.run(new Random(3));
	}
}
