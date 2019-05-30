package com.jobsity.challenge.service;

import com.jobsity.challenge.model.FramesResult;
import com.jobsity.challenge.model.PlayerInputValues;
import com.jobsity.challenge.model.PlayerShots;

public interface IScoreCalculatorService {

    FramesResult calculateScore(final PlayerShots playerShots);

}
