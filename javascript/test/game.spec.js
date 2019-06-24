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

	it("should allow to control the output", function() {
		var result = runGame();
		console.log("This is the result");
		console.log(result);
	});

	it("should control the randomness", function(){
		initialiseRandom(1);
		expect(Math.floor(Math.random() * 6)).to.eq(4);
	});

	it("should verify the result", function(){
		var result = runGame(1);
	
		this.verify(result, {reporters: ["donothing"]});
	});

	it("verify for seed 3", function(){
		var result = runGame(3);
	
		this.verify(result, {reporters: ["donothing"]});
	});

	it("verify for seed 5", function(){
		var result = runGame(5);
	
		this.verify(result, {reporters: ["donothing"]});
	});
});



function runGame(seed=1){
	initialiseRandom(seed);
    console.oldLog = console.log;
    var result = "";
    console.log = function (value) {
        result += value + "\n";
    };

    gameRunner();

    console.log = console.oldLog;
    return result;
}

function initialiseRandom(seed) {
    Math.random = function () {
        var x = Math.sin(seed++) * 10000;
        return x - Math.floor(x);
    }
}
