import Foundation

enum ApprovalError: Error {
    case Error(String)
    case SkipCombination()
}
