package com.jobsity.challenge.model;

import java.util.List;

public class PlayerInputValues {

    private String player;
    private List<String> inputValues;

    protected PlayerInputValues(String player, List<String> inputValues) {
        this.player = player;
        this.inputValues = inputValues;
    }

    public static PlayerInputValues createPlayerInputValues(String player, List<String> inputValues) {
        return new PlayerInputValues(player, inputValues);
    }

    public String getPlayer() {
        return player;
    }

    public List<String> getInputValues() {
        return inputValues;
    }

    @Override
    public String toString() {
        return "PlayerInputValues{" +
                "player='" + player + '\'' +
                ", inputValues=" + inputValues +
                '}';
    }
}
