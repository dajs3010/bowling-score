package com.jobsity.challenge.service.Impl;

import com.jobsity.challenge.exceptions.InvalidScoreException;
import com.jobsity.challenge.service.impl.Validator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class ValidatorTest {

    @InjectMocks
    private Validator validator;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = InvalidScoreException.class)
    public void validateScore_invalidNumberScore_throwException() throws InvalidScoreException {
        final String invalidNumber = "11";
        validator.validateScore(invalidNumber);
    }

    @Test(expected = InvalidScoreException.class)
    public void validateScore_invalidLetterScore_throwException() throws InvalidScoreException {
        final String invalidNumber = "A";
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
}
