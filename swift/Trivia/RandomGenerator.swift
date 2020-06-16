import Foundation

protocol RandomGenerator {
    func number(from: Int, until: Int) -> Int
}

class RealRandomGenerator: RandomGenerator {
    func number(from: Int = 0, until: Int) -> Int {
        Int.random(in: from ..< until)
    }
}
