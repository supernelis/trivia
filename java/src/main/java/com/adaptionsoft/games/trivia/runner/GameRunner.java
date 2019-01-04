
package com.adaptionsoft.games.trivia.runner;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Console;
import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.Players;

import static java.util.Arrays.asList;


public class GameRunner {

	private static boolean notAWinner;

	public static void main(String[] args) {
		run(new Random(), new Game(new Console(), new Players()), "Chet", "Pat", "Sue");
	}

	public static void run(Random random, Game game, String... players) {
		Game aGame = game;

		asList(players).forEach(aGame::add);

		Random rand = random;

		do {

			aGame.roll(rand.nextInt(5) + 1);

			if (rand.nextInt(9) == 7) {
				notAWinner = aGame.wrongAnswer();
			} else {
				notAWinner = aGame.wasCorrectlyAnswered();
			}



		} while (notAWinner);
	}
}
