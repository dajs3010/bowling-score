package com.jobsity.challenge.service.Impl;

import com.jobsity.challenge.model.Shot;
import com.jobsity.challenge.model.type.ShotType;
import com.jobsity.challenge.service.IValidator;
import com.jobsity.challenge.service.impl.LastFrameMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class LastFrameMapperTest {

    @Mock
    private IValidator validator;

    @InjectMocks
    private LastFrameMapper lastFrameMapper;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getLastFrameFirstShot_valueIsMaxPinFalls_returnStrikeShot() {
        String value = "10";
        Integer expectedValue = 10;
        final Shot lastFrameFirstShot = lastFrameMapper.getLastFrameFirstShot(value);
        Assert.assertEquals(Shot.createStrike().getScoreType(), lastFrameFirstShot.getScoreType());
        Assert.assertEquals(expectedValue, lastFrameFirstShot.getValue());
    }

    @Test
    public void getLastFrameFirstShot_valueIsNotMaxPinFalls_returnNumberShot() {
        String value = "5";
        Integer expectedValue = 5;
        final Shot lastFrameFirstShot = lastFrameMapper.getLastFrameFirstShot(value);
        Assert.assertEquals(Shot.createShot(value).getScoreType(), lastFrameFirstShot.getScoreType());
        Assert.assertEquals(expectedValue, lastFrameFirstShot.getValue());
    }

    @Test
    public void getLastFrameFirstShot_valueIsFoul_returnFoulShot() {
        String value = "F";
        Integer expectedValue = 0;
        final Shot lastFrameFirstShot = lastFrameMapper.getLastFrameFirstShot(value);
        Assert.assertEquals(Shot.createFoul().getScoreType(), lastFrameFirstShot.getScoreType());
        Assert.assertEquals(expectedValue, lastFrameFirstShot.getValue());
    }

    @Test
    public void getLastFrameSecondShot_valueIsStrike_returnStrikeShot() {
        String actual = "10";
        String next = "10";
        Shot firstShot = Shot.createStrike();
        Integer expectedValue = 10;
        final Shot lastFrameSecondShot = lastFrameMapper.getLastFrameSecondShot(actual, next, firstShot);
        Assert.assertEquals(Shot.createStrike().getScoreType(), lastFrameSecondShot.getScoreType());
        Assert.assertEquals(expectedValue, lastFrameSecondShot.getValue());
    }

    @Test
    public void getLastFrameSecondShot_valueIsSpare_returnSpareShot() {
        String actual = "7";
        String next = "3";
        Shot firstShot = Shot.createFoul();
        Integer expectedValue = 3;
        Mockito.when(validator.isSpare(actual, next)).thenReturn(true);
        final Shot lastFrameSecondShot = lastFrameMapper.getLastFrameSecondShot(actual, next, firstShot);
        Assert.assertEquals(Shot.createSpare(next).getScoreType(), lastFrameSecondShot.getScoreType());
        Assert.assertEquals(expectedValue, lastFrameSecondShot.getValue());
    }

    @Test
    public void getLastFrameSecondShot_valueIsNotStrikeOrSpare_returnNumberShot() {
        String actual = "7";
        String next = "2";
        Shot firstShot = Shot.createFoul();
        Integer expectedValue = 2;
        Mockito.when(validator.isSpare(actual, next)).thenReturn(false);
        final Shot lastFrameSecondShot = lastFrameMapper.getLastFrameSecondShot(actual, next, firstShot);
        Assert.assertEquals(Shot.createShot(next).getScoreType(), lastFrameSecondShot.getScoreType());
        Assert.assertEquals(expectedValue, lastFrameSecondShot.getValue());
    }

    @Test
    public void getLastFrameLastShot_valueIsStrikeAndBeforeValueWasAStrike_returnStrike() {
        String next = "2";
        String afterNext = "10";
        Shot second = Shot.createStrike();
        Integer expectedValue = 10;
        final Shot lastFrameLastShot2 = lastFrameMapper.getLastFrameLastShot(afterNext, next, second);
        Assert.assertEquals(Shot.createStrike().getScoreType(), lastFrameLastShot2.getScoreType());
        Assert.assertEquals(expectedValue, lastFrameLastShot2.getValue());
    }

    @Test
    public void getLastFrameLastShot_valueIsStrikeAndBeforeValueWasASpare_returnStrike() {
        String next = "2";
        String afterNext = "10";
        Shot second = Shot.createSpare(next);
        Integer expectedValue = 10;
        final Shot lastFrameLastShot2 = lastFrameMapper.getLastFrameLastShot(afterNext, next, second);
        Assert.assertEquals(Shot.createStrike().getScoreType(), lastFrameLastShot2.getScoreType());
        Assert.assertEquals(expectedValue, lastFrameLastShot2.getValue());
    }

    @Test
    public void getLastFrameLastShot_valueIsSpareAndBeforeValueWasNotASpare_returnSpare() {
        String next = "1";
        String afterNext = "9";
        Shot second = Shot.createShot("1");
        Integer expectedValue = 9;
        Mockito.when(validator.isSpare(afterNext, next)).thenReturn(true);
        final Shot lastFrameLastShot2 = lastFrameMapper.getLastFrameLastShot(afterNext, next, second);
        Assert.assertEquals(Shot.createSpare("9").getScoreType(), lastFrameLastShot2.getScoreType());
        Assert.assertEquals(expectedValue, lastFrameLastShot2.getValue());
    }

    @Test
    public void getLastFrameLastShot_valueIsNormalAndBeforeWasAStrike_returnNumberShot() {
        String next = "10";
        String afterNext = "9";
        Shot second = Shot.createStrike();
        Integer expectedValue = 9;
        Mockito.when(validator.isSpare(afterNext, next)).thenReturn(true);
        final Shot lastFrameLastShot2 = lastFrameMapper.getLastFrameLastShot(afterNext, next, second);
        Assert.assertEquals(Shot.createShot("9").getScoreType(), lastFrameLastShot2.getScoreType());
        Assert.assertEquals(expectedValue, lastFrameLastShot2.getValue());
    }

    @Test
    public void getLastFrameLastShot_valueIsNormalAndBeforeWasNotAStrike_returnNumberShot() {
        String next = "9";
        String afterNext = "9";
        Shot second = Shot.createShot("9");
        Integer expectedValue = 9;
        Mockito.when(validator.isSpare(afterNext, next)).thenReturn(false);
        final Shot lastFrameLastShot2 = lastFrameMapper.getLastFrameLastShot(afterNext, next, second);
        Assert.assertEquals(ShotType.NUMBER, lastFrameLastShot2.getScoreType());
        Assert.assertEquals(expectedValue, lastFrameLastShot2.getValue());
    }

}
