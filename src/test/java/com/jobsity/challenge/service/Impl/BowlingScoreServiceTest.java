package com.jobsity.challenge.service.Impl;

import com.jobsity.challenge.exceptions.BowlingScoreException;
import com.jobsity.challenge.model.PlayerInputValues;
import com.jobsity.challenge.model.PlayerShots;
import com.jobsity.challenge.model.Shot;
import com.jobsity.challenge.service.IPlayerShotsMapper;
import com.jobsity.challenge.service.IPrinterService;
import com.jobsity.challenge.service.IScoreCalculatorService;
import com.jobsity.challenge.service.impl.BowlingScoreService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;

public class BowlingScoreServiceTest {

    @Mock
    private IScoreCalculatorService scoreCalculatorService;

    @Mock
    private IPrinterService printerService;

    @Mock
    private IPlayerShotsMapper playerShotsMapper;

    @InjectMocks
    private BowlingScoreService bowlingScoreService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    private final static String PLAYER = "PLAYER";

    @Test
    public void calculateScore_executeCalculateScoreAndPrintResults() throws BowlingScoreException {

        final List<PlayerInputValues> playerInputValues =
                Arrays.asList(
                        PlayerInputValues.createPlayerInputValues("Jeff",
                                Arrays.asList("10", "7", "3", "9", "0", "10", "0", "8", "8", "2", "F", "6",
                                        "10", "10", "10", "8", "1"))
                );

        final Shot foul = Shot.createFoul();
        final Shot strike = Shot.createStrike();
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

        Mockito.when(playerShotsMapper.mapPlayerShots(playerInputValues)).thenReturn(Arrays.asList(shots));

        bowlingScoreService.calculateScore(playerInputValues);

        Mockito.verify(scoreCalculatorService, Mockito.atLeastOnce()).calculateScore(shots);
        Mockito.verify(printerService, Mockito.atLeastOnce()).printResults(ArgumentMatchers.anyList());
    }

}
