//
//  Printer.swift
//  Trivia
//
//  Created by Nelis Boucké on 10/03/2020.
//  Copyright © 2020 Legacy Coderetreat. All rights reserved.
//

public class Printer {
    public func output(_ items: CustomStringConvertible...){
        print(items.map{$0.description}.joined(separator: " "))
    }
}
