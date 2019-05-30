package com.jobsity.challenge.model;

import com.jobsity.challenge.model.type.ShotType;

public class Shot {

    private final static Integer STRIKE_VALUE = 10;
    private final static Integer FOUL_VALUE = 0;
    private final static Integer EMPTY_SHOT_VALUE = 0;

    private ShotType scoreType;
    private Integer value;

    protected Shot(ShotType scoreType, Integer value) {
        this.scoreType = scoreType;
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public ShotType getScoreType() {
        return scoreType;
    }

    @Override
    public String toString() {
        return "Shot{" +
                "scoreType='" + scoreType + '\'' +
                ", value=" + value +
                '}';
    }

    public static Shot createStrike() {
        return new Shot(ShotType.STRIKE, STRIKE_VALUE);
    }

    public static Shot createSpare(String value) {
        return new Shot(ShotType.SPARE, Integer.parseInt(value));
    }

    private static Shot createNumber(String value) {
        return new Shot(ShotType.NUMBER, Integer.parseInt(value));
    }

    public static Shot createEmptyShot() {
        return new Shot(ShotType.EMPTY_SHOT, EMPTY_SHOT_VALUE);
    }

    public static Shot createFoul() {
        return new Shot(ShotType.FOUL, FOUL_VALUE);
    }

    public static Shot createShot(String value) {
        if (ShotType.FOUL.getLabel().equals(value)) {
            return Shot.createFoul();
        }
        return Shot.createNumber(value);
    }

    public boolean isStrike() {
        return ShotType.STRIKE.equals(scoreType);
    }

    public boolean isSpare() {
        return ShotType.SPARE.equals(scoreType);
    }
}
