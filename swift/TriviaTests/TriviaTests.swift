import XCTest

class TriviaTests: XCTestCase {
    
    func test_NotNil() {
        XCTAssertNotNil(Game())
    }
    
    func test_runGame() {
        let random = MockRandomGenerator()
        let printer = StringPrinter()
        let game = Game(printer: printer)
        
        play(random: random, aGame: game)
        
        XCTAssertEqual(goldenMaster, printer.text)
    }
}

class StringPrinter: Printer {
    private(set) var text = ""
    
    func output(_ items: CustomStringConvertible...) {
        text += items.map{$0.description}.joined(separator: " ") + "\n"
    }
}


class MockRandomGenerator: RandomGenerator {

    var until5List = [5,4,2,2,1,2,3,4,1,1,2,2,2,3,5,4,2,1,2,4,3,3,2,1,2,5,1,3,3,5,1,4,3,1,3,1,1,1,3,4,3,4,3,1,3,3,4,2,5,3,3,2,2,4,4,1,3,2,1,5,3,1,5,4,3,1,5,5,1,3,4,4,1,4,5,3,2,3,5,5,1,3,3,5,3,2,5,1,3,2,1,3,3,5,5,5,1,1,1,2]
    
    var until9List = [3,3,5,5,1,7,7,0,0,6,0,3,3,8,5,5,1,2,3,6,2,5,0,5,1,8,5,1,8,0,7,2,7,7,6,2,0,6,1,0,8,7,3,7,4,4,5,8,6,2,0,1,6,0,0,1,5,8,3,4,4,1,5,8,0,4,2,6,5,6,0,4,8,3,3,1,8,5,5,1,2,7,8,0,1,5,4,6,6,3,0,6,6,2,4,1,2,4,0,7]
    
    func number(from: Int, until: Int) -> Int {
        if( until == 5){
            return until5List.popLast()!
        }
        return until9List.popLast()!
    }

}

let goldenMaster = """
Chet was added
They are player number 1
Pat was added
They are player number 2
Sue was added
They are player number 3
Chet is the current player
They have rolled a 2
Chet's new location is 2
The category is Sports
Sports Question 0
Question was incorrectly answered
Chet was sent to the penalty box
Pat is the current player
They have rolled a 1
Pat's new location is 1
The category is Science
Science Question 0
Answer was corrent!!!!
Pat now has 1 Gold Coins.
Sue is the current player
They have rolled a 1
Sue's new location is 1
The category is Science
Science Question 1
Answer was corrent!!!!
Sue now has 1 Gold Coins.
Chet is the current player
They have rolled a 1
Chet is getting out of the penalty box
Chet's new location is 3
The category is Rock
Rock Question 0
Answer was correct!!!!
Chet now has 1 Gold Coins.
Pat is the current player
They have rolled a 5
Pat's new location is 6
The category is Sports
Sports Question 1
Answer was corrent!!!!
Pat now has 2 Gold Coins.
Sue is the current player
They have rolled a 5
Sue's new location is 6
The category is Sports
Sports Question 2
Answer was corrent!!!!
Sue now has 2 Gold Coins.
Chet is the current player
They have rolled a 5
Chet is getting out of the penalty box
Chet's new location is 8
The category is Pop
Pop Question 0
Answer was correct!!!!
Chet now has 2 Gold Coins.
Pat is the current player
They have rolled a 3
Pat's new location is 9
The category is Science
Science Question 2
Answer was corrent!!!!
Pat now has 3 Gold Coins.
Sue is the current player
They have rolled a 3
Sue's new location is 9
The category is Science
Science Question 3
Answer was corrent!!!!
Sue now has 3 Gold Coins.
Chet is the current player
They have rolled a 1
Chet is getting out of the penalty box
Chet's new location is 9
The category is Science
Science Question 4
Answer was correct!!!!
Chet now has 3 Gold Coins.
Pat is the current player
They have rolled a 2
Pat's new location is 11
The category is Rock
Rock Question 1
Answer was corrent!!!!
Pat now has 4 Gold Coins.
Sue is the current player
They have rolled a 3
Sue's new location is 0
The category is Pop
Pop Question 1
Answer was corrent!!!!
Sue now has 4 Gold Coins.
Chet is the current player
They have rolled a 1
Chet is getting out of the penalty box
Chet's new location is 10
The category is Sports
Sports Question 3
Answer was correct!!!!
Chet now has 4 Gold Coins.
Pat is the current player
They have rolled a 5
Pat's new location is 4
The category is Pop
Pop Question 2
Answer was corrent!!!!
Pat now has 5 Gold Coins.
Sue is the current player
They have rolled a 2
Sue's new location is 2
The category is Sports
Sports Question 4
Answer was corrent!!!!
Sue now has 5 Gold Coins.
Chet is the current player
They have rolled a 3
Chet is getting out of the penalty box
Chet's new location is 1
The category is Science
Science Question 5
Answer was correct!!!!
Chet now has 5 Gold Coins.
Pat is the current player
They have rolled a 5
Pat's new location is 9
The category is Science
Science Question 6
Answer was corrent!!!!
Pat now has 6 Gold Coins.

"""





























































