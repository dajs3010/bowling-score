package com.jobsity.challenge.service.impl;

import com.jobsity.challenge.model.FramePinFalls;
import com.jobsity.challenge.model.FramesResult;
import com.jobsity.challenge.model.PlayerShots;
import com.jobsity.challenge.model.Shot;
import com.jobsity.challenge.service.IScoreCalculatorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ScoreCalculatorService implements IScoreCalculatorService {

    public FramesResult calculateScore(PlayerShots playerShots) {

        final List<Integer> scores = new ArrayList<>();
        final FramePinFalls framePinfalls = FramePinFalls.createFramePinFalls();
        int sum = 0;
        int i = 1;

        final List<List<Shot>> inputShots = playerShots.getInputShots();

        for (List<Shot> shots : inputShots) {
            sum += shots.stream().map(Shot::getValue).mapToInt(Integer::intValue).sum();
            if (i == inputShots.size()) {
                framePinfalls.addNormalFrame(shots);
            } else if (shots.get(0).isStrike()) {
                framePinfalls.addStrikeFrame();
            } else {
                framePinfalls.addNormalFrame(Arrays.asList(shots.get(0), shots.get(1)));
            }

            scores.add(sum);
            i++;
        }

        return FramesResult.createFrameResult(scores, framePinfalls, playerShots.getPlayer());
    }
}