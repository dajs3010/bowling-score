package com.jobsity.challenge;

import com.jobsity.challenge.exceptions.BowlingScoreException;
import com.jobsity.challenge.model.PlayerInputValues;
import com.jobsity.challenge.service.IBowlingScoreService;
import com.jobsity.challenge.service.IParserDataService;
import com.jobsity.challenge.service.impl.BowlingScoreService;
import com.jobsity.challenge.service.impl.ParserDataService;
import com.jobsity.challenge.utils.Errors;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class App {

    private IBowlingScoreService bowlingScoreService;
    private IParserDataService parserDataService;

    public static void main(String[] args) {
        try {
            ApplicationContext ctx = SpringApplication.run(App.class, args);

            final String fileName = args[0];

            BowlingScoreService bowlingScoreService = (BowlingScoreService) ctx.getBean("bowlingScoreService");
            ParserDataService parserDataService = (ParserDataService) ctx.getBean("parserDataService");

            final List<String> lines = Files.lines(Paths.get(fileName)).collect(Collectors.toList());

            final List<PlayerInputValues> playersShots = parserDataService.getPlayersInputValues(lines);

            bowlingScoreService.calculateScore(playersShots);

        } catch (IOException e) {
            System.out.print(Errors.FILE_READ_ERROR);
        } catch (BowlingScoreException e) {
            System.out.print(e.getMessage());
        }

    }
}
