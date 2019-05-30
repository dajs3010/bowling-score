package com.jobsity.challenge.model;

import java.util.List;

public class PlayerShots {

    private String player;
    private List<List<Shot>> inputShots;

    protected PlayerShots(String player, List<List<Shot>> inputShots) {
        this.player = player;
        this.inputShots = inputShots;
    }

    public static PlayerShots createPlayerInputShots(String player, List<List<Shot>> inputValues) {
        return new PlayerShots(player, inputValues);
    }

    public String getPlayer() {
        return player;
    }

    public List<List<Shot>> getInputShots() {
        return inputShots;
    }

    @Override
    public String toString() {
        return "PlayerShots{" +
                "player='" + player + '\'' +
                ", inputShots=" + inputShots +
                '}';
    }
}
