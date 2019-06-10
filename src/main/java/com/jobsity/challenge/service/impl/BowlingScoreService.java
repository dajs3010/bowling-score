package com.jobsity.challenge.service.impl;

import com.jobsity.challenge.exceptions.BowlingScoreException;
import com.jobsity.challenge.model.FramesResult;
import com.jobsity.challenge.model.PlayerInputValues;
import com.jobsity.challenge.model.PlayerShots;
import com.jobsity.challenge.service.IBowlingScoreService;
import com.jobsity.challenge.service.IPlayerShotsMapper;
import com.jobsity.challenge.service.IPrinterService;
import com.jobsity.challenge.service.IScoreCalculatorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BowlingScoreService implements IBowlingScoreService {

    private final IScoreCalculatorService scoreCalculatorService;
    private final IPrinterService printerService;
    private final IPlayerShotsMapper playerShotsMapper;

    public BowlingScoreService(IScoreCalculatorService scoreCalculatorService, IPrinterService printerService, IPlayerShotsMapper playerShotsMapper) {
        this.scoreCalculatorService = scoreCalculatorService;
        this.printerService = printerService;
        this.playerShotsMapper = playerShotsMapper;
    }

    public void calculateScore(final List<PlayerInputValues> playerInputValues) throws BowlingScoreException {

        final List<PlayerShots> playersShots = playerShotsMapper.mapPlayerShots(playerInputValues);

        final List<FramesResult> framesResults =
                playersShots.stream().map(shots -> scoreCalculatorService.calculateScore(shots))
                        .collect(Collectors.toList());

        printerService.printResults(framesResults);

    }
}
