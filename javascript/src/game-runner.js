var Game = require("./game.js");

module.exports = function (players=["Chet", "Pat", "Sue"]) {
    var notAWinner = false;

    var game = new Game();

    players.forEach((player) => game.add(player));
    
    do {
        game.roll(Math.floor(Math.random() * 6) + 1);

        if (Math.floor(Math.random() * 10) == 7) {
            notAWinner = game.wrongAnswer();
        } else {
            notAWinner = game.wasCorrectlyAnswered();
        }

    } while (notAWinner);
};  