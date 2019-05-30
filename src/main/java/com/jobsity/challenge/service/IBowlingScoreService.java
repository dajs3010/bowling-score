package com.jobsity.challenge.service;

import com.jobsity.challenge.model.PlayerInputValues;

import java.util.List;

public interface IBowlingScoreService {

    void calculateScore(final List<PlayerInputValues> playersShots);

}
