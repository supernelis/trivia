//
//  Printer.swift
//  Trivia
//
//  Created by Nelis Boucké on 10/03/2020.
//  Copyright © 2020 Legacy Coderetreat. All rights reserved.
//

public protocol Printer {
    func output(_ items: CustomStringConvertible...)
}

public class ConsolePrinter: Printer {
    public func output(_ items: CustomStringConvertible...){
        print(items.map{$0.description}.joined(separator: " "))
    }
}
