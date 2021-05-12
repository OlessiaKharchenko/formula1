package application;

import controller.Parser;
import model.Racer;
import org.junit.jupiter.api.Test;
import util.ResourceFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegrationalParserTest {
    private ResourceFileReader reader = new ResourceFileReader();
    private Parser parser = new Parser(reader);

    @Test
    void createRacers_shouldReturnCorrectList() throws URISyntaxException, IOException {
        List<Racer> actual = parser.createRacers("start_test.log", "end_test.log", "abbreviations_test.txt");
        List<Racer> expected = Arrays.asList(
                new Racer("Daniel Ricciardo", "RED BULL RACING TAG HEUER", Duration.parse("PT1M12.013S")),
                new Racer("Sebastian Vettel", "FERRARI", Duration.parse("PT1M4.415S")),
                new Racer("Lewis Hamilton", "MERCEDES", Duration.parse("PT1M12.46S")));
        assertEquals(actual, expected);
    }
}