//
//  main.swift
//  Trivia
//
//  Created by Oliver Eikemeier on 13.10.15.
//  Copyright © 2015 Legacy Coderetreat. All rights reserved.
//

import Foundation

func play(
    players: [String] = ["Chet","Pat","Sue"],
    random: RandomGenerator = RealRandomGenerator(),
    aGame: Game = Game()
) {
    var notAWinner: Bool

    players.forEach { player in
        _ = aGame.add(playerName: player)
    }
    
    repeat {
        
        aGame.roll(roll: random.number(from: 1, until: 5))
        
        if (Int(random.number(from: 0, until: 9)) == 7) {
            notAWinner = aGame.wrongAnswer()
        } else {
            notAWinner = aGame.wasCorrectlyAnswered()
        }
        
    } while (notAWinner)
    
}

play()
