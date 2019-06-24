package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.approvaltests.Approvals;
import org.approvaltests.combinations.CombinationApprovals;
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

	@Test
	public void can_run_controlled_game_for_multiple_seeds() {
		Integer[] seeds = {3,5};

		Approvals.verifyAll(seeds,seed -> runGame(seed));
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

	public String runGameForSeedAndPlayers(Integer seed, Players players) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream, true);

		PrintStream oldOut = System.out;
		System.setOut(printStream);

		Random rand = new Random(seed);
		GameRunner.runGame(rand, players.players);

		System.setOut(oldOut);

		return outputStream.toString();
	}

	@Test
	public void can_run_controlled_game_for_multiple_players() throws Exception {
		Integer[] seeds = {3, 5};
		Players[] playerCombinations = new Players[] {
				new Players(),
				new Players("Chet"),
				new Players("Chet", "Jean"),
				new Players("Chet", "Jean", "Matteo", "Nelis", "Jan", "Piet"),
		};

		CombinationApprovals.verifyAllCombinations(this::runGameForSeedAndPlayers, seeds, playerCombinations);
	}
}
