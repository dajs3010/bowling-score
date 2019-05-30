package com.jobsity.challenge.exceptions;

import com.jobsity.challenge.utils.Errors;

public class InvalidScoreException extends BowlingScoreException {

    public InvalidScoreException() {
        super(Errors.SHOT_VALUE_ERROR);
    }

}
