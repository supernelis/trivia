import XCTest

class TriviaTests: XCTestCase {
    
    func test_NotNil() {
        XCTAssertNotNil(Game())
    }
    
    func test_runGame() {
        let printer = StringPrinter()
        let game = Game(printer: printer)
        
        XCTAssertEqual("", printer.text)
    }
}

class StringPrinter: Printer {
    private(set) var text = ""
    
    func output(_ items: CustomStringConvertible...) {
        text += items.map{$0.description}.joined(separator: " ")
    }
}
