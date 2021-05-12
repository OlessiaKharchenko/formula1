package view;

import model.Racer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.lineSeparator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SeparatorFormatterTest {

    @Mock
    private Formatter mock;

    @Test
    void format_shouldReturnStringWithSeparator_whenLineIndexLessThanRacersNumber() {
        SeparatorFormatter separatorFormatter = new SeparatorFormatter(mock, 2);
        List<Racer> input = Arrays.asList(
                new Racer("Daniel Ricciardo", "RED BULL RACING TAG HEUER", Duration.parse("PT1M12.013S")),
                new Racer("Sebastian Vettel", "FERRARI", Duration.parse("PT1M4.415S")),
                new Racer("Lewis Hamilton", "MERCEDES", Duration.parse("PT1M12.46S")));
        String stringWithoutSeparator =
                "1. Sebastian Vettel | FERRARI                   | 01:04.415" + lineSeparator()
                        + "2. Daniel Ricciardo | RED BULL RACING TAG HEUER | 01:12.013" + lineSeparator()
                        + "3. Lewis Hamilton   | MERCEDES                  | 01:12.460";
        when(mock.format(input)).thenReturn(stringWithoutSeparator);
        String expected = "1. Sebastian Vettel | FERRARI                   | 01:04.415" + lineSeparator()
                + "2. Daniel Ricciardo | RED BULL RACING TAG HEUER | 01:12.013" + lineSeparator()
                + "-----------------------------------------------------------" + lineSeparator()
                + "3. Lewis Hamilton   | MERCEDES                  | 01:12.460";
        String actual = separatorFormatter.format(input);
        assertEquals(expected, actual);
    }

    @Test
    void format_shouldReturnStringWithoutSeparator_whenLineIndexMoreThanRacersNumber() {
        SeparatorFormatter separatorFormatter = new SeparatorFormatter(mock, 4);
        List<Racer> input = Arrays.asList(
                new Racer("Daniel Ricciardo", "RED BULL RACING TAG HEUER", Duration.parse("PT1M12.013S")),
                new Racer("Sebastian Vettel", "FERRARI", Duration.parse("PT1M4.415S")),
                new Racer("Lewis Hamilton", "MERCEDES", Duration.parse("PT1M12.46S")));
        String expected = "1. Sebastian Vettel | FERRARI                   | 01:04.415" + lineSeparator()
                + "2. Daniel Ricciardo | RED BULL RACING TAG HEUER | 01:12.013" + lineSeparator()
                + "3. Lewis Hamilton   | MERCEDES                  | 01:12.460";
        when(mock.format(input)).thenReturn(expected);
        String actual = separatorFormatter.format(input);
        assertSame(expected, actual);
    }

    @Test
    void shouldThrowException_whenNegativeLineIndex() {
        assertThrows(IllegalArgumentException.class, () -> new SeparatorFormatter(mock, -1));
    }
}