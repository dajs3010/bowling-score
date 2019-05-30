package com.jobsity.challenge.service.Impl;

import com.jobsity.challenge.model.FramesResult;
import com.jobsity.challenge.model.PlayerShots;
import com.jobsity.challenge.model.Shot;
import com.jobsity.challenge.service.impl.ScoreCalculatorService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class ScoreCalculatorServiceTest {

    @InjectMocks
    private ScoreCalculatorService scoreCalculator;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    private final static String PLAYER = "Player";
    private final Shot foul = Shot.createFoul();
    private final Shot emptyShot = Shot.createEmptyShot();
    private final Shot strike = Shot.createStrike();
    private final Shot zero = Shot.createShot("0");

    @Test
    public void calculateScore_perfectScore() {

        final PlayerShots shots = PlayerShots.createPlayerInputShots(PLAYER,
                Arrays.asList(
                        Arrays.asList(strike, Shot.createShot("10"), Shot.createShot("10")),
                        Arrays.asList(strike, Shot.createShot("10"), Shot.createShot("10")),
                        Arrays.asList(strike, Shot.createShot("10"), Shot.createShot("10")),
                        Arrays.asList(strike, Shot.createShot("10"), Shot.createShot("10")),
                        Arrays.asList(strike, Shot.createShot("10"), Shot.createShot("10")),
                        Arrays.asList(strike, Shot.createShot("10"), Shot.createShot("10")),
                        Arrays.asList(strike, Shot.createShot("10"), Shot.createShot("10")),
                        Arrays.asList(strike, Shot.createShot("10"), Shot.createShot("10")),
                        Arrays.asList(strike, Shot.createShot("10"), Shot.createShot("10")),
                        Arrays.asList(strike, strike, strike)
                ));

        final List<Shot> pinFalls = Arrays.asList(emptyShot, strike, emptyShot, strike,
                emptyShot, strike, emptyShot, strike, emptyShot, strike,
                emptyShot, strike, emptyShot, strike, emptyShot, strike,
                emptyShot, strike, strike, strike, strike);

        final FramesResult framesResult = scoreCalculator.calculateScore(shots);

        for (int i = 0; i < pinFalls.size(); i++) {
            Assert.assertEquals(pinFalls.get(i).getValue(), framesResult.getFramePinfalls().getPinFalls().get(i).getValue());
            Assert.assertEquals(pinFalls.get(i).getScoreType(), framesResult.getFramePinfalls().getPinFalls().get(i).getScoreType());
        }
        Assert.assertEquals(Arrays.asList(30, 60, 90, 120, 150, 180, 210, 240, 270, 300), framesResult.getScore());
        Assert.assertEquals(PLAYER, framesResult.getPlayer());
    }

    @Test
    public void calculateScore_zerosScore() {

        final PlayerShots shots = PlayerShots.createPlayerInputShots(PLAYER,
                Arrays.asList(
                        Arrays.asList(zero, zero),
                        Arrays.asList(zero, zero),
                        Arrays.asList(zero, zero),
                        Arrays.asList(zero, zero),
                        Arrays.asList(zero, zero),
                        Arrays.asList(zero, zero),
                        Arrays.asList(zero, zero),
                        Arrays.asList(zero, zero),
                        Arrays.asList(zero, zero),
                        Arrays.asList(zero, zero)
                ));

        final List<Shot> pinFalls = Arrays.asList(zero, zero, zero, zero,
                zero, zero, zero, zero, zero, zero,
                zero, zero, zero, zero, zero, zero,
                zero, zero, zero, zero);

        final FramesResult framesResult = scoreCalculator.calculateScore(shots);

        for (int i = 0; i < pinFalls.size(); i++) {
            Assert.assertEquals(pinFalls.get(i).getValue(), framesResult.getFramePinfalls().getPinFalls().get(i).getValue());
            Assert.assertEquals(pinFalls.get(i).getScoreType(), framesResult.getFramePinfalls().getPinFalls().get(i).getScoreType());
        }
        Assert.assertEquals(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0), framesResult.getScore());
        Assert.assertEquals(PLAYER, framesResult.getPlayer());
    }

    @Test
    public void calculateScore_foulsScore() {

        final PlayerShots shots = PlayerShots.createPlayerInputShots(PLAYER,
                Arrays.asList(
                        Arrays.asList(foul, foul),
                        Arrays.asList(foul, foul),
                        Arrays.asList(foul, foul),
                        Arrays.asList(foul, foul),
                        Arrays.asList(foul, foul),
                        Arrays.asList(foul, foul),
                        Arrays.asList(foul, foul),
                        Arrays.asList(foul, foul),
                        Arrays.asList(foul, foul),
                        Arrays.asList(foul, foul)
                ));

        final List<Shot> pinFalls = Arrays.asList(foul, foul, foul, foul,
                foul, foul, foul, foul, foul, foul,
                foul, foul, foul, foul, foul, foul,
                foul, foul, foul, foul);

        final FramesResult framesResult = scoreCalculator.calculateScore(shots);

        for (int i = 0; i < pinFalls.size(); i++) {
            Assert.assertEquals(pinFalls.get(i).getValue(), framesResult.getFramePinfalls().getPinFalls().get(i).getValue());
            Assert.assertEquals(pinFalls.get(i).getScoreType(), framesResult.getFramePinfalls().getPinFalls().get(i).getScoreType());
        }
        Assert.assertEquals(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0), framesResult.getScore());
        Assert.assertEquals(PLAYER, framesResult.getPlayer());
    }

    @Test
    public void calculateScore_jeffResults() {
        final PlayerShots shots = PlayerShots.createPlayerInputShots(PLAYER,
                Arrays.asList(
                        Arrays.asList(strike, Shot.createShot("7"), Shot.createShot("3")),
                        Arrays.asList(Shot.createShot("7"), Shot.createSpare("3"), Shot.createShot("9")),
                        Arrays.asList(Shot.createShot("9"), Shot.createShot("0")),
                        Arrays.asList(strike, Shot.createShot("0"), Shot.createShot("8")),
                        Arrays.asList(Shot.createShot("0"), Shot.createShot("8")),
                        Arrays.asList(Shot.createShot("8"), Shot.createSpare("2"), foul),
                        Arrays.asList(foul, Shot.createShot("6")),
                        Arrays.asList(strike, Shot.createShot("10"), Shot.createShot("10")),
                        Arrays.asList(strike, Shot.createShot("10"), Shot.createShot("8")),
                        Arrays.asList(strike, Shot.createShot("8"), Shot.createShot("1"))
                ));
        final List<Shot> pinFalls = Arrays.asList(emptyShot, strike, Shot.createShot("7"), Shot.createSpare("3"),
                Shot.createShot("9"), Shot.createShot("0"), emptyShot, strike, Shot.createShot("0"),
                Shot.createShot("8"), Shot.createShot("8"), Shot.createSpare("2"), foul, Shot.createShot("6"),
                emptyShot, strike, emptyShot, strike, strike, Shot.createShot("8"), Shot.createShot("1"));

        final FramesResult framesResult = scoreCalculator.calculateScore(shots);

        for (int i = 0; i < pinFalls.size(); i++) {
            Assert.assertEquals(pinFalls.get(i).getValue(), framesResult.getFramePinfalls().getPinFalls().get(i).getValue());
            Assert.assertEquals(pinFalls.get(i).getScoreType(), framesResult.getFramePinfalls().getPinFalls().get(i).getScoreType());
        }
        Assert.assertEquals(Arrays.asList(20, 39, 48, 66, 74, 84, 90, 120, 148, 167), framesResult.getScore());
        Assert.assertEquals(PLAYER, framesResult.getPlayer());
    }

    @Test
    public void calculateScore_johnResults() {
        final PlayerShots shots = PlayerShots.createPlayerInputShots(PLAYER,
                Arrays.asList(
                        Arrays.asList(Shot.createShot("3"), Shot.createSpare("7"), Shot.createShot("6")),
                        Arrays.asList(Shot.createShot("6"), Shot.createShot("3")),
                        Arrays.asList(strike, Shot.createShot("8"), Shot.createShot("1")),
                        Arrays.asList(Shot.createShot("8"), Shot.createShot("1")),
                        Arrays.asList(strike, strike, Shot.createShot("9")),
                        Arrays.asList(strike, Shot.createShot("9"), Shot.createShot("0")),
                        Arrays.asList(Shot.createShot("9"), Shot.createShot("0")),
                        Arrays.asList(Shot.createShot("7"), Shot.createSpare("3"), Shot.createShot("4")),
                        Arrays.asList(Shot.createShot("4"), Shot.createShot("4")),
                        Arrays.asList(strike, Shot.createShot("9"), Shot.createShot("0"))
                ));
        final List<Shot> pinFalls = Arrays.asList(Shot.createShot("3"), Shot.createSpare("7"), Shot.createShot("6"),
                Shot.createShot("3"), emptyShot, strike, Shot.createShot("8"), Shot.createShot("1"),
                emptyShot, strike, emptyShot, strike, Shot.createShot("9"), Shot.createShot("0"),
                Shot.createShot("7"), Shot.createSpare("3"), Shot.createShot("4"), Shot.createShot("4"),
                strike, Shot.createShot("9"), Shot.createShot("0"));

        final FramesResult framesResult = scoreCalculator.calculateScore(shots);

        for (int i = 0; i < pinFalls.size(); i++) {
            Assert.assertEquals(pinFalls.get(i).getValue(), framesResult.getFramePinfalls().getPinFalls().get(i).getValue());
            Assert.assertEquals(pinFalls.get(i).getScoreType(), framesResult.getFramePinfalls().getPinFalls().get(i).getScoreType());
        }
        Assert.assertEquals(Arrays.asList(16, 25, 44, 53, 82, 101, 110, 124, 132, 151), framesResult.getScore());
        Assert.assertEquals(PLAYER, framesResult.getPlayer());
    }

}
