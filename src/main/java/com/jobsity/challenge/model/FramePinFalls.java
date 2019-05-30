package com.jobsity.challenge.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FramePinFalls {

    private List<Shot> pinFalls;

    protected FramePinFalls() {
        this.pinFalls = new ArrayList<>();
    }

    public static FramePinFalls createFramePinFalls() {
        return new FramePinFalls();
    }

    public void addStrikeFrame() {
        this.pinFalls.addAll(Arrays.asList(Shot.createEmptyShot(), Shot.createStrike()));
    }

    public void addNormalFrame(List<Shot> shots) {
        this.pinFalls.addAll(shots);
    }


    public List<Shot> getPinFalls() {
        return pinFalls;
    }

    @Override
    public String toString() {
        return "FramePinFalls{" +
                "pinFalls=" + pinFalls +
                '}';
    }
}
