package com.jobsity.challenge.service.Impl;

import com.jobsity.challenge.model.PlayerInputValues;
import com.jobsity.challenge.model.PlayerShots;
import com.jobsity.challenge.model.Shot;
import com.jobsity.challenge.service.ILastFrameMapper;
import com.jobsity.challenge.service.IValidator;
import com.jobsity.challenge.service.impl.PlayerShotsMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class PlayerShotsMapperTest {

    @Mock
    private ILastFrameMapper lastFrameShotsMapper;

    @Mock
    private IValidator validator;

    @InjectMocks
    private PlayerShotsMapper mapper;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    private final static String PLAYER = "PLAYER";

    @Test
    public void mapPlayerShots() {

        final List<String> inputValues =
                Arrays.asList("10", "7", "3", "9", "0", "10",
                        "0", "8", "8", "2", "F", "6",
                        "10", "10", "10", "8", "1");
        final PlayerInputValues playerInputValues =
                PlayerInputValues.createPlayerInputValues(PLAYER, inputValues);


        final Shot foul = Shot.createFoul();
        final Shot strike = Shot.createStrike();
        final PlayerShots expectedShots = PlayerShots.createPlayerInputShots(PLAYER,
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

        Mockito.when(validator.isSpare("7", "3")).thenReturn(true);
        Mockito.when(validator.isSpare("8", "2")).thenReturn(true);

        Mockito.when(lastFrameShotsMapper.getLastFrameFirstShot("10")).thenReturn(strike);
        final Shot eightPinFallShot = Shot.createShot("8");
        Mockito.when(lastFrameShotsMapper.getLastFrameSecondShot("10", "8", strike)).thenReturn(eightPinFallShot);
        Mockito.when(lastFrameShotsMapper.getLastFrameLastShot("1", "8", eightPinFallShot)).thenReturn(Shot.createShot("1"));

        final List<PlayerShots> playerShots =
                mapper.mapPlayerShots(Arrays.asList(playerInputValues));

        Assert.assertEquals(expectedShots.getInputShots().toString(), playerShots.get(0).getInputShots().toString());
        Assert.assertEquals(expectedShots.getPlayer(), playerShots.get(0).getPlayer());
    }
}
