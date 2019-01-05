
package com.adaptionsoft.games.trivia.runner;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Console;
import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.Player;
import com.adaptionsoft.games.uglytrivia.Players;

import static java.util.Arrays.asList;


public class GameRunner {

	public static void main(String[] args) {
		run(new Random(), new Console(), "Chet", "Pat", "Sue");
	}

	public static void run(Random random, Console console, String... playersName) {
		Players players = new Players();
		asList(playersName).forEach(playerName -> add(playerName, players, console));

		Game game = new Game(console, players);
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

	private static void add(String playerName, Players players, Console console) {
		players.addPlayer(new Player(playerName));

		console.print(playerName + " was added");
		console.print("They are player number " + players.numberOfPlayers());
	}
}
