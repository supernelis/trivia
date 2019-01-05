
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
		asList(players).forEach(game::add);

		Dice dice = new Dice(random);

		do {

			game.roll(dice.roll());

			if (random.nextInt(9) == 7) {
				notAWinner = game.wrongAnswer();
			} else {
				notAWinner = game.wasCorrectlyAnswered();
			}

		} while (notAWinner);
	}
}
