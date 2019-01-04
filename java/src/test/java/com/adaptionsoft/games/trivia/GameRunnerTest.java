package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import com.adaptionsoft.games.uglytrivia.Console;
import com.adaptionsoft.games.uglytrivia.Game;
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
		Approvals.verifyAll(seeds, seed -> runGame(seed, new Players("Chet", "Pat", "Sue")));
	}

	@Test
	public void can_run_a_game_with_different_number_of_players() throws Exception {
		Integer[] seeds = new Integer[]{3, 5};
		Players[] playersCombination = new Players[] {
				new Players(),
				new Players("Chet"),
				new Players("Chet", "Pat"),
				new Players("Chet", "Pat", "Sue"),
				new Players("Chet", "Pat", "Sue", "Fizz"),
				new Players("Chet", "Pat", "Sue", "Fizz", "Buzz"),
				new Players("Chet", "Pat", "Sue", "Fizz", "Buzz", "Foo")
		};

		CombinationApprovals
				.verifyAllCombinations(
						this::runGame,
						seeds,
						playersCombination
				);
	}

	private String runGame(int seed, Players players) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		PrintStream printer = new PrintStream(byteArrayOutputStream, true);
		Game game = new Game(new Console(printer));

		GameRunner.run(new Random(seed), game, players.values());

		return byteArrayOutputStream.toString();
	}

	private class Players {
		private String[] players;

		public Players(String ... players) {
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
