package com.adaptionsoft.games.uglytrivia;

import java.io.PrintStream;

public class Console {
    private PrintStream printer;

    public Console() {
        this(System.out);
    }

    public Console(PrintStream printer) {
        this.printer = printer;
    }

    public void print(String message) {
        printer.println(message);
    }
}
