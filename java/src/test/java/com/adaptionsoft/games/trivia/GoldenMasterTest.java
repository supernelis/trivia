package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

public class GoldenMasterTest {

	@Test
	public void can_run_a_controlled_game_test() {
		Random rand = new Random(1);
		String result = runGame(rand);

		System.out.println(result);
	}

	public String runGame(Random rand) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream, true);

		PrintStream oldOut = System.out;
		System.setOut(printStream);

		GameRunner.runGame(rand);

		System.setOut(oldOut);

		return outputStream.toString();
	}
}
