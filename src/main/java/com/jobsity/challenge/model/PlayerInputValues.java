package com.jobsity.challenge.model;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerInputValues that = (PlayerInputValues) o;
        return Objects.equals(player, that.player) &&
                Objects.equals(inputValues, that.inputValues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, inputValues);
    }
}
