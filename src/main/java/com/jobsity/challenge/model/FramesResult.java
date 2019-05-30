package com.jobsity.challenge.model;

import java.util.List;

public class FramesResult {

    private List<Integer> score;
    private FramePinFalls framePinfalls;
    private String player;

    protected FramesResult(List<Integer> score, FramePinFalls framePinfalls, String player) {
        this.score = score;
        this.framePinfalls = framePinfalls;
        this.player = player;
    }

    public static FramesResult createFrameResult(List<Integer> score, FramePinFalls framesShots, String player) {
        return new FramesResult(score, framesShots, player);
    }

    public List<Integer> getScore() {
        return score;
    }

    public FramePinFalls getFramePinfalls() {
        return framePinfalls;
    }

    public String getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return "FramesResult{" +
                "score=" + score +
                ", framePinfalls=" + framePinfalls +
                ", player='" + player + '\'' +
                '}';
    }
}
