package com.jobsity.challenge.service;

import com.jobsity.challenge.exceptions.InvalidScoreException;

public interface IValidator {

    void validateFileSize(long size) throws InvalidScoreException;

    void validateScore(String score) throws InvalidScoreException;

    void validateLine(String[] inputLine) throws InvalidScoreException;

    boolean isSpare(String firstValue, String secondValue);

}
