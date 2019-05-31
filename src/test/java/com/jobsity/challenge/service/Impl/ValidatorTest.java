package com.jobsity.challenge.service.Impl;

import com.jobsity.challenge.exceptions.InvalidScoreException;
import com.jobsity.challenge.service.impl.Validator;
import com.jobsity.challenge.utils.Errors;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class ValidatorTest {

    @InjectMocks
    private Validator validator;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void validateScore_invalidNumberScore_throwException() throws InvalidScoreException {
        final String invalidNumber = "11";
        expectedException.expect(InvalidScoreException.class);
        expectedException.expectMessage(Errors.SHOT_VALUE_ERROR);
        validator.validateScore(invalidNumber);
    }

    @Test
    public void validateScore_invalidLetterScore_throwException() throws InvalidScoreException {
        final String invalidNumber = "A";
        expectedException.expect(InvalidScoreException.class);
        expectedException.expectMessage(Errors.SHOT_VALUE_ERROR);
        validator.validateScore(invalidNumber);
    }

    @Test
    public void validateScore_validNumberScore_neverThrowException() throws InvalidScoreException {
        final String validNumber = "8";
        validator.validateScore(validNumber);
    }

    @Test
    public void validateScore_validLetterScore_neverThrowException() throws InvalidScoreException {
        final String validNumber = "F";
        validator.validateScore(validNumber);
    }

    @Test
    public void isSpare_valuesSumResultInLessThanTen_returnFalse() {
        final String firstValue = "1";
        final String secondValue = "8";
        Assert.assertFalse(validator.isSpare(firstValue, secondValue));
    }

    @Test
    public void isSpare_valuesAreFouls_returnFalse() {
        final String firstValue = "F";
        final String secondValue = "F";
        Assert.assertFalse(validator.isSpare(firstValue, secondValue));
    }

    @Test
    public void isSpare_valuesSumResultIsTen_returnFalse() {
        final String firstValue = "8";
        final String secondValue = "2";
        Assert.assertTrue(validator.isSpare(firstValue, secondValue));
    }

    @Test
    public void validateFileSize_sizeIsZero_throwError() throws InvalidScoreException {
        final long size = 0;
        expectedException.expect(InvalidScoreException.class);
        expectedException.expectMessage(Errors.EMPTY_FILE_ERROR);
        validator.validateFileSize(size);
    }

    @Test
    public void validateFileSize_sizeIsNotZero_notThrowError() throws InvalidScoreException {
        final long size = 300;
        validator.validateFileSize(size);
    }

    @Test
    public void validateLine_lineArrayIsNotTwo_throwError() throws InvalidScoreException {
        final String[] inputLine = {"David", "2", "3"};
        expectedException.expect(InvalidScoreException.class);
        expectedException.expectMessage(Errors.LINE_STRUCTURE_ERROR);
        validator.validateLine(inputLine);
    }

    @Test
    public void validateLine_lineArrayIsTwo_notThrowError() throws InvalidScoreException {
        final String[] inputLine = {"David", "2"};
        validator.validateLine(inputLine);
    }
}
