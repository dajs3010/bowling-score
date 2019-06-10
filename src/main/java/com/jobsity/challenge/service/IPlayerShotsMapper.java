package com.jobsity.challenge.service;

import com.jobsity.challenge.exceptions.BowlingScoreException;
import com.jobsity.challenge.model.PlayerInputValues;
import com.jobsity.challenge.model.PlayerShots;

import java.util.List;

public interface IPlayerShotsMapper {

    List<PlayerShots> mapPlayerShots(List<PlayerInputValues> playerInputValues) throws BowlingScoreException;

}
