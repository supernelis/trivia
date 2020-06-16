//
//  Game.swift
//  Trivia
//
//  Created by Oliver Eikemeier on 13.10.15.
//  Copyright © 2015 Legacy Coderetreat. All rights reserved.
//

import Foundation

public class Game {
    var players = [String]()
    var places = [Int](repeating: 0, count: 6)
    var purses  = [Int](repeating: 0, count: 6)
    var inPenaltyBox  = [Bool](repeating: false, count: 6)
    
    var popQuestions = [String]()
    var scienceQuestions = [String]()
    var sportsQuestions = [String]()
    var rockQuestions = [String]()
    
    var currentPlayer = 0
    var isGettingOutOfPenaltyBox = false
    
    private var printer: Printer = ConsolePrinter()
    
    public convenience init(printer: Printer){
        self.init()
        self.printer = printer
    }
    
    public  init(){
    	for i in 0..<50 {
			popQuestions.append("Pop Question \(i)")
			scienceQuestions.append(("Science Question \(i)"))
			sportsQuestions.append(("Sports Question \(i)"))
            rockQuestions.append(createRockQuestion(index: i))
    	}
    }

    public func createRockQuestion(index: Int) -> String{
		return "Rock Question \(index)"
	}
	
	public func isPlayable() -> Bool {
		return howManyPlayers() >= 2
	}

    public func add(playerName: String) -> Bool {
		
		
	    players.append(playerName)
	    places[howManyPlayers()] = 0
	    purses[howManyPlayers()] = 0
	    inPenaltyBox[howManyPlayers()] = false
	    
	    printer.output(playerName, "was added")
	    printer.output("They are player number", players.count)
		return true
	}
	
	public func howManyPlayers() -> Int {
		return players.count
	}

    public func roll(roll: Int) {
		printer.output(players[currentPlayer], "is the current player")
		printer.output("They have rolled a", roll)
		
		if inPenaltyBox[currentPlayer] {
			if roll % 2 != 0 {
				isGettingOutOfPenaltyBox = true
				
				printer.output(players[currentPlayer], "is getting out of the penalty box")
				places[currentPlayer] = places[currentPlayer] + roll
                if places[currentPlayer] > 11 {places[currentPlayer] = places[currentPlayer] - 12}
				
				printer.output(players[currentPlayer]
						+ "'s new location is",
						places[currentPlayer])
				printer.output("The category is", currentCategory())
				askQuestion()
			} else {
				printer.output(players[currentPlayer], "is not getting out of the penalty box")
				isGettingOutOfPenaltyBox = false
				}
			
		} else {
		
			places[currentPlayer] = places[currentPlayer] + roll
            if places[currentPlayer] > 11 {places[currentPlayer] = places[currentPlayer] - 12}
			
			printer.output(players[currentPlayer]
					+ "'s new location is",
					places[currentPlayer])
			printer.output("The category is", currentCategory())
			askQuestion()
		}
		
	}

 	private func askQuestion() {
        if currentCategory() == "Pop" {
            printer.output(popQuestions.removeFirst())}
        if currentCategory() == "Science"{
            printer.output(scienceQuestions.removeFirst())}
        if currentCategory() == "Sports"{
            printer.output(sportsQuestions.removeFirst())}
        if currentCategory() == "Rock"{
            printer.output(rockQuestions.removeFirst())}
	}
	
	
	private func currentCategory() -> String {
        if places[currentPlayer] == 0 {return "Pop"}
		if places[currentPlayer] == 4 {return "Pop"}
		if places[currentPlayer] == 8 {return "Pop"}
		if places[currentPlayer] == 1 {return "Science"}
		if places[currentPlayer] == 5 {return "Science"}
		if places[currentPlayer] == 9 {return "Science"}
		if places[currentPlayer] == 2 {return "Sports"}
		if places[currentPlayer] == 6 {return "Sports"}
		if places[currentPlayer] == 10 {return "Sports"}
		return "Rock"
	}

	public func wasCorrectlyAnswered() -> Bool {
		if inPenaltyBox[currentPlayer]{
			if isGettingOutOfPenaltyBox {
				printer.output("Answer was correct!!!!")
				purses[currentPlayer] += 1
				printer.output(players[currentPlayer],
						"now has",
						purses[currentPlayer],
						"Gold Coins.")
				
				let winner = didPlayerWin
				currentPlayer += 1
                if currentPlayer == players.count {currentPlayer = 0}
				
				return winner
			} else {
				currentPlayer += 1
                if currentPlayer == players.count {currentPlayer = 0}
				return true
			}
			
			
			
		} else {
		
			printer.output("Answer was corrent!!!!")
			purses[currentPlayer] += 1
			printer.output(players[currentPlayer],
					"now has",
                    purses[currentPlayer],
					"Gold Coins.")
			
			let winner = didPlayerWin
			currentPlayer += 1
            if currentPlayer == players.count {currentPlayer = 0}
			
			return winner
		}
	}
	
	public func wrongAnswer()->Bool{
        printer.output("Question was incorrectly answered")
		printer.output(players[currentPlayer], "was sent to the penalty box")
		inPenaltyBox[currentPlayer] = true
		
		currentPlayer += 1
        if currentPlayer == players.count {currentPlayer = 0}
		return true
	}


    private var didPlayerWin: Bool {
		return !(purses[currentPlayer] == 6)
	}
}
