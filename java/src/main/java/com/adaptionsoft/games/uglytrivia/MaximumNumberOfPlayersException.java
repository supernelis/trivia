package com.adaptionsoft.games.uglytrivia;

public class MaximumNumberOfPlayersException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Too Many Players";
    }
}
