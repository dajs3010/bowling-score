package com.jobsity.challenge.service.impl;

import com.jobsity.challenge.exceptions.InvalidScoreException;
import com.jobsity.challenge.model.PlayerInputValues;
import com.jobsity.challenge.service.IParserDataService;
import com.jobsity.challenge.service.IValidator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ParserDataService implements IParserDataService {

    private final IValidator validator;

    public ParserDataService(IValidator validator) {
        this.validator = validator;
    }

    private static final String WHITE_SPACE = " ";

    public List<PlayerInputValues> getPlayersInputValues(final List<String> lines) throws InvalidScoreException {

        final Map<String, List<String>> shotsPerPlayer = new HashMap<>();

        for (String line : lines) {
            String[] inputLine = line.split(WHITE_SPACE);
            validator.validateLine(inputLine);
            final String player = inputLine[0];
            final String value = inputLine[1];
            validator.validateScore(value);
            if (!shotsPerPlayer.containsKey(player)) {
                List<String> firstValue = new ArrayList<>();
                firstValue.add(value);
                shotsPerPlayer.put(player, firstValue);
            } else {
                shotsPerPlayer.get(player).add(value);
            }
        }

        return shotsPerPlayer.entrySet().stream().map(entry ->
                PlayerInputValues.createPlayerInputValues(entry.getKey(), entry.getValue())
        ).collect(Collectors.toList());

    }
}
