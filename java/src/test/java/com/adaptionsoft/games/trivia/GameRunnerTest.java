package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import com.adaptionsoft.games.uglytrivia.Game;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

public class GameRunnerTest {

	@Test
	public void can_run_a_game() throws Exception {
		Integer[] seeds = new Integer[]{3, 5};
		Approvals.verifyAll(seeds, this::runGame);
	}

	private String runGame(int seed) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		Game game = new Game() {
			@Override
			protected PrintStream printer() {
				return new PrintStream(byteArrayOutputStream, true);
			}
		};

		GameRunner.run(new Random(seed), game);

		return byteArrayOutputStream.toString();
	}

}
