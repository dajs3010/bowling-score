package testintegration.com.jobsity.challenge;

import com.jobsity.challenge.App;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@SpringBootTest
public class BowlingScoreIntegrationTest {

    private static final String JEFF_AND_JOHN_MATCH_FILE = "./scores/score.txt";
    private static final String PERFECT_SCORE_FILE = "./scores/perfect-score.txt";
    private static final String WORST_SCORE_FILE = "./scores/worst-score.txt";

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void calculateJeffAndJohnMatch() {
        final String[] strings = {JEFF_AND_JOHN_MATCH_FILE};
        String expectedJeffPinFalls = "Pinfalls\t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\tF\t6\t\tX\t\tX\tX\t8\t1";
        String expectedJeffScore = "Score\t\t20\t\t39\t\t48\t\t66\t\t74\t\t84\t\t90\t\t120\t\t148\t\t167";
        String expectedJohnPinFalls = "Pinfalls\t3\t/\t6\t3\t\tX\t8\t1\t\tX\t\tX\t9\t0\t7\t/\t4\t4\tX\t9\t0";
        String expectedJohnScore = "Score\t\t16\t\t25\t\t44\t\t53\t\t82\t\t101\t\t110\t\t124\t\t132\t\t151";
        App.main(strings);
        String[] splitOutContent = outContent.toString().split("\n");
        Assert.assertEquals(expectedJeffPinFalls, splitOutContent[3].trim());
        Assert.assertEquals(expectedJeffScore, splitOutContent[4].trim());
        Assert.assertEquals(expectedJohnPinFalls, splitOutContent[6].trim());
        Assert.assertEquals(expectedJohnScore, splitOutContent[7].trim());
    }

    @Test
    public void calculatePerfectScore() {
        final String[] strings = {PERFECT_SCORE_FILE};
        String expectedPerfectPinFalls = "Pinfalls\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\tX\tX\tX";
        String expectedPerfectScore = "Score\t\t30\t\t60\t\t90\t\t120\t\t150\t\t180\t\t210\t\t240\t\t270\t\t300";
        App.main(strings);
        String[] splitOutContent = outContent.toString().split("\n");
        Assert.assertEquals(expectedPerfectPinFalls, splitOutContent[3].trim());
        Assert.assertEquals(expectedPerfectScore, splitOutContent[4].trim());
    }

    @Test
    public void calculateWorstScore() {
        final String[] strings = {WORST_SCORE_FILE};
        String expectedWorstPinFalls = "Pinfalls\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0";
        String expectedWorstScore = "Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0";
        App.main(strings);
        String[] splitOutContent = outContent.toString().split("\n");
        Assert.assertEquals(expectedWorstPinFalls, splitOutContent[3].trim());
        Assert.assertEquals(expectedWorstScore, splitOutContent[4].trim());
    }

}
