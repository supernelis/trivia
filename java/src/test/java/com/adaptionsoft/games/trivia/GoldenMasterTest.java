package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GoldenMasterTest {

	@Test
	public void can_run_a_controlled_game_test() {
		String result = runGame();

		System.out.println(result);
	}

	public String runGame() {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream, true);

		PrintStream oldOut = System.out;
		System.setOut(printStream);

		GameRunner.runGame();

		System.setOut(oldOut);

		return outputStream.toString();
	}
}
