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
        
        XCTAssertEqual(GoldenMaster.output, printer.text)
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
