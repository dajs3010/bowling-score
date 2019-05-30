package com.jobsity.challenge.service;

import com.jobsity.challenge.exceptions.InvalidScoreException;

public interface IValidator {

    void validateScore(String score) throws InvalidScoreException;

    boolean isSpare(String firstValue, String secondValue);

}
