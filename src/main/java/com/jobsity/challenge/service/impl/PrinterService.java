package com.jobsity.challenge.service.impl;

import com.jobsity.challenge.model.FramePinFalls;
import com.jobsity.challenge.model.FramesResult;
import com.jobsity.challenge.service.IPrinterService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.jobsity.challenge.model.type.ShotType.NUMBER;

@Service
public class PrinterService implements IPrinterService {

    public void printResults(final List<FramesResult> framesResults) {
        System.out.println("\nFramePinFalls\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10");
        framesResults.forEach(framesResult -> {
            final List<Integer> score = framesResult.getScore();
            final FramePinFalls framePinfalls = framesResult.getFramePinfalls();
            final String player = framesResult.getPlayer();
            System.out.println(player);
            printPinFalls(framePinfalls);
            printScore(score);
            System.out.print("\n");
        });
    }

    private void printPinFalls(final FramePinFalls framePinfalls) {
        System.out.print("Pinfalls\t");
        framePinfalls.getPinFalls().forEach(pinFall -> {
            System.out.print(pinFall.getScoreType().equals(NUMBER) ?
                    pinFall.getValue() : pinFall.getScoreType().getLabel());
            System.out.print("\t");
        });
    }

    private void printScore(final List<Integer> score) {
        System.out.print("\nScore\t\t");
        score.forEach(value -> System.out.print(value + "\t\t"));
    }

}
