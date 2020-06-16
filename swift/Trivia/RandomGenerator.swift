import Foundation

class RandomGenerator {
    func number() -> Int {
        Int(arc4random_uniform(5)) + 1
    }
}
