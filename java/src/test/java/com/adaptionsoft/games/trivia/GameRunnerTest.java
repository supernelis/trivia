package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import com.adaptionsoft.games.uglytrivia.Game;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class GameRunnerTest {

	@Test
	public void can_run_a_game() throws Exception {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		Game game = new Game() {
			@Override
			protected PrintStream printer() {
				return new PrintStream(byteArrayOutputStream, true);
			}
		};

		GameRunner.run(new Random(3), game);

		assertEquals(EXPECTED_RESULT, byteArrayOutputStream.toString());
	}

	public static final String EXPECTED_RESULT = "Chet was added\n" +
			"They are player number 1\n" +
			"Pat was added\n" +
			"They are player number 2\n" +
			"Sue was added\n" +
			"They are player number 3\n" +
			"Chet is the current player\n" +
			"They have rolled a 5\n" +
			"Chet's new location is 5\n" +
			"The category is Science\n" +
			"Science Question 0\n" +
			"Answer was corrent!!!!\n" +
			"Chet now has 1 Gold Coins.\n" +
			"Pat is the current player\n" +
			"They have rolled a 1\n" +
			"Pat's new location is 1\n" +
			"The category is Science\n" +
			"Science Question 1\n" +
			"Question was incorrectly answered\n" +
			"Pat was sent to the penalty box\n" +
			"Sue is the current player\n" +
			"They have rolled a 4\n" +
			"Sue's new location is 4\n" +
			"The category is Pop\n" +
			"Pop Question 0\n" +
			"Answer was corrent!!!!\n" +
			"Sue now has 1 Gold Coins.\n" +
			"Chet is the current player\n" +
			"They have rolled a 5\n" +
			"Chet's new location is 10\n" +
			"The category is Sports\n" +
			"Sports Question 0\n" +
			"Question was incorrectly answered\n" +
			"Chet was sent to the penalty box\n" +
			"Pat is the current player\n" +
			"They have rolled a 5\n" +
			"Pat is getting out of the penalty box\n" +
			"Pat's new location is 6\n" +
			"The category is Sports\n" +
			"Sports Question 1\n" +
			"Answer was correct!!!!\n" +
			"Pat now has 1 Gold Coins.\n" +
			"Sue is the current player\n" +
			"They have rolled a 1\n" +
			"Sue's new location is 5\n" +
			"The category is Science\n" +
			"Science Question 2\n" +
			"Answer was corrent!!!!\n" +
			"Sue now has 2 Gold Coins.\n" +
			"Chet is the current player\n" +
			"They have rolled a 3\n" +
			"Chet is getting out of the penalty box\n" +
			"Chet's new location is 1\n" +
			"The category is Science\n" +
			"Science Question 3\n" +
			"Answer was correct!!!!\n" +
			"Chet now has 2 Gold Coins.\n" +
			"Pat is the current player\n" +
			"They have rolled a 2\n" +
			"Pat is not getting out of the penalty box\n" +
			"Sue is the current player\n" +
			"They have rolled a 2\n" +
			"Sue's new location is 7\n" +
			"The category is Rock\n" +
			"Rock Question 0\n" +
			"Answer was corrent!!!!\n" +
			"Sue now has 3 Gold Coins.\n" +
			"Chet is the current player\n" +
			"They have rolled a 2\n" +
			"Chet is not getting out of the penalty box\n" +
			"Pat is the current player\n" +
			"They have rolled a 2\n" +
			"Pat is not getting out of the penalty box\n" +
			"Sue is the current player\n" +
			"They have rolled a 1\n" +
			"Sue's new location is 8\n" +
			"The category is Pop\n" +
			"Pop Question 1\n" +
			"Answer was corrent!!!!\n" +
			"Sue now has 4 Gold Coins.\n" +
			"Chet is the current player\n" +
			"They have rolled a 4\n" +
			"Chet is not getting out of the penalty box\n" +
			"Pat is the current player\n" +
			"They have rolled a 3\n" +
			"Pat is getting out of the penalty box\n" +
			"Pat's new location is 9\n" +
			"The category is Science\n" +
			"Science Question 4\n" +
			"Answer was correct!!!!\n" +
			"Pat now has 2 Gold Coins.\n" +
			"Sue is the current player\n" +
			"They have rolled a 2\n" +
			"Sue's new location is 10\n" +
			"The category is Sports\n" +
			"Sports Question 2\n" +
			"Answer was corrent!!!!\n" +
			"Sue now has 5 Gold Coins.\n" +
			"Chet is the current player\n" +
			"They have rolled a 4\n" +
			"Chet is not getting out of the penalty box\n" +
			"Pat is the current player\n" +
			"They have rolled a 3\n" +
			"Pat is getting out of the penalty box\n" +
			"Pat's new location is 0\n" +
			"The category is Pop\n" +
			"Pop Question 2\n" +
			"Answer was correct!!!!\n" +
			"Pat now has 3 Gold Coins.\n" +
			"Sue is the current player\n" +
			"They have rolled a 3\n" +
			"Sue's new location is 1\n" +
			"The category is Science\n" +
			"Science Question 5\n" +
			"Answer was corrent!!!!\n" +
			"Sue now has 6 Gold Coins.\n";
}
