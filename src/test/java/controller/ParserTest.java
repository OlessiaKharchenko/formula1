package controller;

import model.Racer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import util.ResourceFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class ParserTest {

    @Mock
    private ResourceFileReader mock;
    @InjectMocks
    private Parser parser;

    @Test
    void createRacers_shouldReturnCorrectResult() throws URISyntaxException, IOException {
        when(mock.readFile("abbreviations.txt")).thenReturn(Stream.of(
                "DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER",
                "SVF_Sebastian Vettel_FERRARI",
                "LHM_Lewis Hamilton_MERCEDES"));
        when(mock.readFile("start.log")).thenReturn(Stream.of(
                "SVF2018-05-24_12:02:58.917", "DRR2018-05-24_12:14:12.054", "LHM2018-05-24_12:18:20.125"));
        when(mock.readFile("end.log")).thenReturn(Stream.of(
                "LHM2018-05-24_12:19:32.585", "SVF2018-05-24_12:04:03.332", "DRR2018-05-24_12:15:24.067"));
        List<Racer> actual = parser.createRacers("start.log", "end.log", "abbreviations.txt");
        List<Racer> expected = Arrays.asList(
                new Racer("Daniel Ricciardo", "RED BULL RACING TAG HEUER", Duration.parse("PT1M12.013S")),
                new Racer("Sebastian Vettel", "FERRARI", Duration.parse("PT1M4.415S")),
                new Racer("Lewis Hamilton", "MERCEDES", Duration.parse("PT1M12.46S")));
        assertEquals(expected, actual);
    }

    @Test
    void createRacers_shouldReadGivenFiles() throws URISyntaxException, IOException {
        List<Racer> actual = parser.createRacers("start.log", "end.log", "abbreviations.txt");
        verify(mock, times(1)).readFile("start.log");
        verify(mock, times(1)).readFile("end.log");
        verify(mock, times(1)).readFile("abbreviations.txt");
        verifyNoMoreInteractions(mock);
    }

    @Test
    void createRacers_shouldThrowException_whenFirstInputEmpty() {
        assertThrows(IllegalArgumentException.class, () -> parser.createRacers(null, "end.log", "abbreviations.txt"));
    }

    @Test
    void createRacers_shouldThrowException_whenSecondInputEmpty() {
        assertThrows(IllegalArgumentException.class, () -> parser.createRacers("start.log", null, "abbreviations.txt"));
    }

    @Test
    void createRacers_shouldThrowException_whenThirdInputEmpty() {
        assertThrows(IllegalArgumentException.class, () -> parser.createRacers("start.log", "end.log", null));
    }
}