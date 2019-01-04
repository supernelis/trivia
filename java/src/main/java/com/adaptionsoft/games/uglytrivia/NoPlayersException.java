package com.adaptionsoft.games.uglytrivia;

public class NoPlayersException extends RuntimeException {

    @Override
    public String getMessage() {
        return "No Players!";
    }
}
