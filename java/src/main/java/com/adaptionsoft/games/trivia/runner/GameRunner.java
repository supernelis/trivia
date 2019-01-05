
package com.adaptionsoft.games.trivia.runner;

import java.util.Optional;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.*;

import static com.adaptionsoft.games.uglytrivia.Game.GAME_ON_GOING;
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
        while (playTurn()) ;
    }

    private boolean playTurn() {
        Optional<Question> question = game.roll(dice.roll());

        if (question.isPresent()) {
            Answer answer = answeringService.answer();

            return game.handleAnswer(answer);
        }

        return GAME_ON_GOING;
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
