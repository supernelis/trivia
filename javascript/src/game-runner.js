const reporter = require("./reporter");
const Dice = require("./dice");
const Answer = require("./answer");
const Game = require("./game.js");

module.exports = function () {
    const dice = new Dice();
    const game = new Game(reporter);

    ['Chet', 'Pat', 'Sue'].forEach(game.add);

    do {
        game.roll(dice.roll());
    } while (answer());

    function answer() {
        return game.verify(new Answer());
    }
}