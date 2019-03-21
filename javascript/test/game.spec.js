require('approvals').mocha();
var expect = require('chai').expect;
var Game = require("../src/game.js");
var gameRunner = require("../src/game-runner.js")

describe("The tests", function () {
	it("should pass", function () {
		expect(true).to.equal(true);
	});

	it("should access game", function () {
		expect(Game).to.be.a('function');
	});

	it("should access game runner", function () {
		expect(gameRunner).to.be.a('function');
	});
});
