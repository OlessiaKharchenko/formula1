package view;

import model.Racer;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.lineSeparator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class SimpleFormatterTest {

    private SimpleFormatter formatter = new SimpleFormatter();

    @Test
    void format_shouldReturnCorrectString() {
        List<Racer> racers = Arrays.asList(
                new Racer("Daniel Ricciardo", "RED BULL RACING TAG HEUER", Duration.parse("PT1M12.013S")),
                new Racer("Sebastian Vettel", "FERRARI", Duration.parse("PT1M4.415S")));
        String actual = formatter.format(racers);
        String expected = "1. Sebastian Vettel | FERRARI                   | 01:04.415" + lineSeparator()
                + "2. Daniel Ricciardo | RED BULL RACING TAG HEUER | 01:12.013";
        assertEquals(expected, actual);
    }

    @Test
    void format_shouldThrowException_whenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> formatter.format(null));
    }

    @Test
    void format_shouldReturnEmptyString_whenInputIsEmpty() {
        List<Racer> racers = new ArrayList<>();
        String expected = "";
        String actual = formatter.format(racers);
        assertEquals(expected, actual);
    }
}