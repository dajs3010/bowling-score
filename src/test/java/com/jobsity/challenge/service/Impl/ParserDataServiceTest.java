package com.jobsity.challenge.service.Impl;

import com.jobsity.challenge.exceptions.InvalidScoreException;
import com.jobsity.challenge.model.PlayerInputValues;
import com.jobsity.challenge.service.IValidator;
import com.jobsity.challenge.service.impl.ParserDataService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class ParserDataServiceTest {

    @Mock
    private IValidator validator;

    @InjectMocks
    private ParserDataService parserDataService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getGroupedShots_inputStreamOfLines_returnGroupedScore() throws InvalidScoreException {
        List<String> lines = Arrays.asList("Jeff 10",
                "John 3",
                "John 7",
                "Jeff 7",
                "Jeff 3",
                "John 6",
                "John 3",
                "Jeff 9",
                "Jeff 0",
                "John 10",
                "Jeff 10",
                "John 8",
                "John 1",
                "Jeff 0",
                "Jeff 8",
                "John 10",
                "Jeff 8",
                "Jeff 2",
                "John 10",
                "Jeff F",
                "Jeff 6",
                "John 9",
                "John 0",
                "Jeff 10",
                "John 7",
                "John 3",
                "Jeff 10",
                "John 4",
                "John 4",
                "Jeff 10",
                "Jeff 8",
                "Jeff 1",
                "John 10",
                "John 9",
                "John 0");

        final List<PlayerInputValues> expectedPlayerShots =
                Arrays.asList(
                        PlayerInputValues.createPlayerInputValues("Jeff",
                                Arrays.asList("10", "7", "3", "9", "0", "10", "0", "8", "8", "2", "F", "6",
                                        "10", "10", "10", "8", "1")),
                        PlayerInputValues.createPlayerInputValues("John",
                                Arrays.asList("3", "7", "6", "3", "10", "8", "1", "10", "10", "9", "0", "7",
                                        "3", "4", "4", "10", "9", "0"))
                );

        final List<PlayerInputValues> playersShots = parserDataService.getPlayersInputValues(lines);
        Assert.assertEquals(expectedPlayerShots, playersShots);
    }

    @Test
    public void getGroupedShots_whenAnInputHasALetterInValue_throwInvalidScoreException() throws InvalidScoreException {
        List<String> lines = Arrays.asList("Jeff A", "John 10");
        Mockito.doThrow(InvalidScoreException.class).when(validator).validateScore("A");
        expectedException.expect(InvalidScoreException.class);
        parserDataService.getPlayersInputValues(lines);
    }

    @Test
    public void getGroupedShots_whenAnInputHasANegativeNumberInValue_throwInvalidScoreException() throws InvalidScoreException {
        List<String> lines = Arrays.asList("Jeff -5", "John 10");
        Mockito.doThrow(InvalidScoreException.class).when(validator).validateScore("-5");
        expectedException.expect(InvalidScoreException.class);
        parserDataService.getPlayersInputValues(lines);
    }

    @Test
    public void getGroupedShots_whenAnInputHasANumberOutOfRangeInValue_throwInvalidScoreException() throws InvalidScoreException {
        List<String> lines = Arrays.asList("Jeff 11", "John 10");
        Mockito.doThrow(InvalidScoreException.class).when(validator).validateScore("11");
        expectedException.expect(InvalidScoreException.class);
        parserDataService.getPlayersInputValues(lines);
    }

}
