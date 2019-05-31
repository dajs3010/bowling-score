package com.jobsity.challenge.service.impl;

import com.jobsity.challenge.model.Shot;
import com.jobsity.challenge.service.ILastFrameMapper;
import com.jobsity.challenge.service.IValidator;
import com.jobsity.challenge.utils.Constants;
import org.springframework.stereotype.Service;

@Service
public class LastFrameMapper implements ILastFrameMapper {

    private IValidator validator;

    public LastFrameMapper(IValidator validator) {
        this.validator = validator;
    }

    public Shot getLastFrameFirstShot(String value) {
        if (Constants.MAX_PINFALL_STRING.equals(value)) {
            return Shot.createStrike();
        }
        return Shot.createShot(value);
    }

    public Shot getLastFrameSecondShot(String actual, String next, Shot first) {
        if (Constants.MAX_PINFALL_STRING.equals(next) && first.isStrike()) {
            return Shot.createStrike();
        } else if (!first.isStrike() && validator.isSpare(actual, next)) {
            return Shot.createSpare(next);
        }
        return Shot.createShot(next);
    }

    public Shot getLastFrameLastShot(String afterNext, String next, Shot second) {
        if (validator.isSpare(afterNext, next) && !second.isSpare() && !second.isStrike()) {
            return Shot.createSpare(afterNext);
        } else if (Constants.MAX_PINFALL_STRING.equals(afterNext) && (second.isSpare() || second.isStrike())) {
            return Shot.createStrike();
        } else {
            return Shot.createShot(afterNext);
        }
    }

}
