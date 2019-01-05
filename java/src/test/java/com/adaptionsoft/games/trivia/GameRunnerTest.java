package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import com.adaptionsoft.games.uglytrivia.Console;
import org.approvaltests.Approvals;
import org.approvaltests.combinations.CombinationApprovals;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

public class GameRunnerTest {

	@Test
	public void can_run_a_game() throws Exception {
		Integer[] seeds = new Integer[]{3, 5};
		Approvals.verifyAll(seeds, seed -> runGame(seed, new PlayerList("Chet", "Pat", "Sue")));
	}

	@Test
	public void can_run_a_game_with_different_number_of_players() throws Exception {
		Integer[] seeds = new Integer[]{3, 5};
		PlayerList[] playersCombination = new PlayerList[] {
				new PlayerList(),
				new PlayerList("Chet"),
				new PlayerList("Chet", "Pat"),
				new PlayerList("Chet", "Pat", "Sue"),
				new PlayerList("Chet", "Pat", "Sue", "Fizz"),
				new PlayerList("Chet", "Pat", "Sue", "Fizz", "Buzz"),
				new PlayerList("Chet", "Pat", "Sue", "Fizz", "Buzz", "Foo")
		};

		CombinationApprovals
				.verifyAllCombinations(
						this::runGame,
						seeds,
						playersCombination
				);
	}

	private String runGame(int seed, PlayerList players) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		PrintStream printer = new PrintStream(byteArrayOutputStream, true);
		Console console = new Console(printer);

		GameRunner gameRunner = GameRunner.createGameRunner(new Random(seed), console, players.values());
		gameRunner.run();

		return byteArrayOutputStream.toString();
	}

	private class PlayerList {
		private String[] players;

		public PlayerList(String ... players) {
			this.players = players;
		}

		public String[] values() {
			return players;
		}

		@Override
		public String toString() {
			return String.join(",", players);
		}
	}
}
