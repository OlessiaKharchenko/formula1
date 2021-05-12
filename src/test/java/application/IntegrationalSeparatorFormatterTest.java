package application;

import controller.Parser;
import model.Racer;
import org.junit.jupiter.api.Test;
import util.ResourceFileReader;
import view.SeparatorFormatter;
import view.SimpleFormatter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static java.lang.System.lineSeparator;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegrationalSeparatorFormatterTest {

    private ResourceFileReader reader = new ResourceFileReader();
    private Parser parser = new Parser(reader);
    private SeparatorFormatter formatter = new SeparatorFormatter(new SimpleFormatter(), 2);

    @Test
    void format_shouldReturnStringWithSeparator() throws URISyntaxException, IOException {
        List<Racer> racers = parser.createRacers("start_test.log", "end_test.log", "abbreviations_test.txt");
        String actual = formatter.format(racers);
        String expected = "1. Sebastian Vettel | FERRARI                   | 01:04.415"+ lineSeparator()
                        + "2. Daniel Ricciardo | RED BULL RACING TAG HEUER | 01:12.013"+ lineSeparator()
                        + "-----------------------------------------------------------"+ lineSeparator()
                        + "3. Lewis Hamilton   | MERCEDES                  | 01:12.460";
        assertEquals(actual, expected);
    }
}