package com.jobsity.challenge.service.impl;

import com.jobsity.challenge.exceptions.InvalidScoreException;
import com.jobsity.challenge.service.IValidator;
import com.jobsity.challenge.utils.Constants;
import com.jobsity.challenge.utils.Errors;
import org.springframework.stereotype.Service;

import static com.jobsity.challenge.model.type.ShotType.FOUL;

@Service
public class Validator implements IValidator {

    private static final String SCORE_REGEXP = "^(?:[0-9]|10|F)$";
    private static final Integer MAX_LINE_ARRAY_LENGTH = 2;

    public void validateFileSize(long size) throws InvalidScoreException {
        if (size == 0) {
            throw new InvalidScoreException(Errors.EMPTY_FILE_ERROR);
        }
    }

    public void validateScore(String score) throws InvalidScoreException {
        if (!score.matches(SCORE_REGEXP)) {
            throw new InvalidScoreException(Errors.SHOT_VALUE_ERROR);
        }
    }

    public void validateLine(String[] inputLine) throws InvalidScoreException {
        if (inputLine.length != MAX_LINE_ARRAY_LENGTH) {
            throw new InvalidScoreException(Errors.LINE_STRUCTURE_ERROR);
        }
    }

    public boolean isSpare(String firstValue, String secondValue) {
        return getIntegerValue(firstValue) + getIntegerValue(secondValue) == Constants.MAX_PINFALL_VALUE;
    }

    private int getIntegerValue(String input) {
        return FOUL.getLabel().equals(input) || Constants.EMPTY.equals(input) ? 0 : Integer.parseInt(input);
    }

}
