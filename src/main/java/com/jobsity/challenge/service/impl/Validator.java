package com.jobsity.challenge.service.impl;

import com.jobsity.challenge.exceptions.InvalidScoreException;
import com.jobsity.challenge.service.IValidator;
import com.jobsity.challenge.utils.Constants;
import org.springframework.stereotype.Service;

import static com.jobsity.challenge.model.type.ShotType.FOUL;

@Service
public class Validator implements IValidator {

    private static final String SCORE_REGEXP = "^(?:[0-9]|10|F)$";

    public void validateScore(String score) throws InvalidScoreException {
        if(!score.matches(SCORE_REGEXP)) {
            throw new InvalidScoreException();
        }
    }

    public boolean isSpare(String firstValue, String secondValue) {
        return getIntegerValue(firstValue) + getIntegerValue(secondValue) == Constants.MAX_PINFALL_VALUE;
    }

    private int getIntegerValue(String input) {
        return input.equals(FOUL.getLabel()) || input.equals(Constants.EMPTY) ? 0 : Integer.parseInt(input);
    }

}
