import Foundation

public class UseReporterFromAnnotations: ApprovalFailureReporter {
    public init() {}
    public func report(received: String, approved: String) {
        ReporterFactory.get().report(received: received, approved: approved)
    }
}
