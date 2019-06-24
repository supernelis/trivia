package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.approvaltests.Approvals;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

public class GoldenMasterTest {

	@Test
	public void can_run_a_controlled_game_test() {
		String result = runGame(1);

		System.out.println(result);
	}

	@Test
	public void can_run_a_controlled_game() {
		String result = runGame(1);

		Approvals.verify(result);
	}

	public String runGame(int seed) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream, true);

		PrintStream oldOut = System.out;
		System.setOut(printStream);

		GameRunner.runGame(new Random(seed));

		System.setOut(oldOut);

		return outputStream.toString();
	}
}
