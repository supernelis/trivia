const Player = require('./player');
const Players = require('./players');
const QuestionsDeck = require('./questionsDeck');
const Board = require('./board');

module.exports = function (reporter) {
	const gameContinue = true;
	const players = new Players();
	const board = new Board();
	const questionsDeck = new QuestionsDeck();

	let currentPlayer;

	this.add = function (playerName) {
		players.add(new Player(playerName));
		currentPlayer = currentPlayer || players.nextAfter();
		report(playerName + " was added");
		report("They are player number " + players.count());
	};

	this.roll = function (roll) {
		report(currentPlayer.name + " is the current player");
		report("They have rolled a " + roll);

		if (isCurrentPlayerGettingOutOfThePenaltyBox(roll)) {
			board.removeFromPenaltyBox(currentPlayer);
			report(currentPlayer.name + " is getting out of the penalty box");
		}

		if (board.isInPenaltyBox(currentPlayer)) {
			report(currentPlayer.name + " is not getting out of the penalty box");
		} else {
			move(roll);
		}
	};

	this.verify = function(answer) {
		return answer.isCorrect() ? correctAnswer() : wrongAnswer();
	} 

	function correctAnswer () {
		const player = currentPlayer;
		nextPlayer();

		if (board.isInPenaltyBox(player)) {
			return gameContinue;
		}

		report("Answer was correct!!!!");
		player.addCoin();
		report(player.name + " now has " + player.coins + " Gold Coins.");
		return !player.hasWon();
	};

	function wrongAnswer() {
		report('Question was incorrectly answered');
		report(currentPlayer.name + " was sent to the penalty box");
		board.addToThePenaltyBox(currentPlayer)
		nextPlayer();
		return gameContinue;
	};

	function move(steps) {
		board.move(currentPlayer, steps);
		const currentPlayerPosition = board.positionOf(currentPlayer);
		report(currentPlayer.name + "'s new location is " + currentPlayerPosition);
		report("The category is " + board.categoryFor(currentPlayerPosition));
		askQuestionFor(currentPlayerPosition);
	}

	function askQuestionFor(position) {
		const positionCategory = board.categoryFor(position);
		const question = questionsDeck.questionFor(positionCategory);
		report(question);
	}

	function nextPlayer() {
		currentPlayer = players.nextAfter(currentPlayer);
	}

	function isCurrentPlayerGettingOutOfThePenaltyBox(roll) {
		return board.isInPenaltyBox(currentPlayer) && isGettingOut(roll);
	}

	function isGettingOut(roll) {
		return roll % 2 != 0;
	}

	function report(message) {
		reporter.report(message);
	}
};