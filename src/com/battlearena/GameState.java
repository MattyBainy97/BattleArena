package com.battlearena;

public enum GameState {

    IN_LOBBY(true), IN_GAME(false), END_GAME(false), RESET(false);

    private static GameState currentState;
    private boolean joinable;

    GameState(boolean joinable){

        this.joinable = joinable;

    }

    public static void setState(GameState state){

        currentState = state;

    }

    public static boolean isState(GameState state){

        return currentState == state;

    }

    public static boolean isJoinable(){

        return currentState.joinable;

    }

    public static GameState getState(){

        return currentState;

    }

}
