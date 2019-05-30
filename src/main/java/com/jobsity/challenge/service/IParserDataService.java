package com.jobsity.challenge.service;

import com.jobsity.challenge.exceptions.InvalidScoreException;
import com.jobsity.challenge.model.PlayerInputValues;

import java.util.List;

public interface IParserDataService {

    List<PlayerInputValues> getPlayersInputValues(final List<String> lines) throws InvalidScoreException;

}
