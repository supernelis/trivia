require('approvals').mocha();
var expect = require('chai').expect;
var Game = require("../src/game.js");
var gameRunner = require("../src/game-runner.js")

describe("The tests", function () {
	it("should pass", function () {
		var seed = 1;
		Math.random = function () {
			var x = Math.sin(seed++) * 10000;
			return x - Math.floor(x);
		}
		console.oldLog = console.log;
		var result = "";
		console.log = function (value) {
			result += value + "\n";
		};
		gameRunner();
		this.verify(result, { reporters: [] });
	});

	it("should access game", function () {
		expect(Game).to.be.a('function');
	});

	it("should access game runner", function () {
		expect(gameRunner).to.be.a('function');
	});
});
