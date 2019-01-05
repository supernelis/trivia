
package com.adaptionsoft.games.trivia.runner;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Console;
import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.Players;

import static java.util.Arrays.asList;


public class GameRunner {

	public static void main(String[] args) {
		run(new Random(), new Game(new Console(), new Players()), "Chet", "Pat", "Sue");
	}

	public static void run(Random random, Game game, String... players) {
		asList(players).forEach(game::add);

		Dice dice = new Dice(random);
		AnsweringService answeringService = new AnsweringService(random);

		boolean notAWinner;
		do {

			game.roll(dice.roll());

			Answer answer = answeringService.answer();
			if (answer.isCorrect()) {
				notAWinner = game.wasCorrectlyAnswered();
			} else {
				notAWinner = game.wrongAnswer();
			}

		} while (notAWinner);
	}
}
