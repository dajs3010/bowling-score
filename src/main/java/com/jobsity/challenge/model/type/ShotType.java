package com.jobsity.challenge.model.type;

public enum ShotType {

    STRIKE("X"), FOUL("F"), SPARE("/"), EMPTY_SHOT(""), NUMBER("#");

    private final String label;

    ShotType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "ShotType{" +
                "label='" + label + '\'' +
                '}';
    }
}
