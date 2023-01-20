package com.example.rps_game_with_gui;

public enum Hands {

    ROCK ("rock"),
    PAPER ("paper"),
    SCISSORS("scissors");

    private String option;

    Hands (String option) {
        this.option = option;
    }
    public String getOption(){
        return option;
    }
    public static Hands chooseGuess(){
        return values()[(int)(Math.random() * values().length)];
    }

}
