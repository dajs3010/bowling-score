package com.jobsity.challenge.service.impl;

import com.jobsity.challenge.model.PlayerInputValues;
import com.jobsity.challenge.model.PlayerShots;
import com.jobsity.challenge.model.Shot;
import com.jobsity.challenge.service.ILastFrameMapper;
import com.jobsity.challenge.service.IPlayerShotsMapper;
import com.jobsity.challenge.service.IValidator;
import com.jobsity.challenge.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerShotsMapper implements IPlayerShotsMapper {

    private ILastFrameMapper lastFrameShotsService;
    private IValidator validator;

    public PlayerShotsMapper(ILastFrameMapper lastFrameShotsService, IValidator validator) {
        this.lastFrameShotsService = lastFrameShotsService;
        this.validator = validator;
    }

    public List<PlayerShots> mapPlayerShots(List<PlayerInputValues> playerInputValues) {

        return playerInputValues.stream().map(inputValue -> {
            int shotIndex = 0;
            int resultIndex = 1;

            List<List<Shot>> inputShots = new ArrayList<>();

            do {
                List<String> inputValues = inputValue.getInputValues();

                String actual = inputValues.get(shotIndex);
                String next = inputValues.get(shotIndex + 1);
                String afterNext = shotIndex + 2 < inputValues.size() ?
                        inputValues.get(shotIndex + 2) : Constants.EMPTY;

                if (resultIndex == Constants.TOTAL_FRAMES) {
                    Shot first = lastFrameShotsService.getLastFrameFirstShot(actual);
                    Shot second = lastFrameShotsService.getLastFrameSecondShot(actual, next, first);
                    List<Shot> lastShots = new ArrayList<>(Arrays.asList(first, second));
                    if (!afterNext.equals(Constants.EMPTY)) {
                        lastShots.add(lastFrameShotsService.getLastFrameLastShot(afterNext, next, second));
                    }
                    inputShots.add(lastShots);
                } else if (actual.equals(Constants.MAX_PINFALL_STRING)) {
                    inputShots.add(Arrays.asList(Shot.createStrike(),
                            Shot.createShot(next),
                            Shot.createShot(afterNext)));
                    shotIndex++;
                } else if (validator.isSpare(actual, next)) {
                    inputShots.add(Arrays.asList(Shot.createShot(actual),
                            Shot.createSpare(next),
                            Shot.createShot(afterNext)));
                    shotIndex += 2;
                } else {
                    inputShots.add(Arrays.asList(Shot.createShot(actual),
                            Shot.createShot(next)));
                    shotIndex += 2;
                }
                resultIndex++;
            } while (resultIndex <= Constants.TOTAL_FRAMES);

            return PlayerShots.createPlayerInputShots(inputValue.getPlayer(), inputShots);

        }).collect(Collectors.toList());
    }

}
