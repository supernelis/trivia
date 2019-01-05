
package com.adaptionsoft.games.trivia.runner;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Console;
import com.adaptionsoft.games.uglytrivia.Game;
import com.adaptionsoft.games.uglytrivia.Player;
import com.adaptionsoft.games.uglytrivia.Players;

import static java.util.Arrays.asList;


public class GameRunner {

	private final Game game;
	private final Dice dice;
	private final AnsweringService answeringService;

	public static GameRunner createGameRunner(Random random, Console console, String... playersName) {
		Dice dice = new Dice(random);
		AnsweringService answeringService = new AnsweringService(random);
		Game game = new Game(console, makePlayersWithNames(playersName, console));
		return new GameRunner(game, dice, answeringService);
	}

	public GameRunner(Game game, Dice dice, AnsweringService answeringService) {
		this.game = game;
		this.dice = dice;
		this.answeringService = answeringService;
	}

	public void run() {
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

	private static Players makePlayersWithNames(String[] playersName, Console console) {
		Players players = new Players();
		asList(playersName).forEach(playerName -> add(playerName, players, console));
		return players;
	}

	private static void add(String playerName, Players players, Console console) {
		players.addPlayer(new Player(playerName));

		console.print(playerName + " was added");
		console.print("They are player number " + players.numberOfPlayers());
	}

	public static void main(String[] args) {
		GameRunner gameRunner = createGameRunner(new Random(), new Console(), "Chet", "Pat", "Sue");
		gameRunner.run();
	}
}
