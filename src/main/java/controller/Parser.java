package controller;

import model.Racer;
import util.ResourceFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Parser {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
    private static final int ABBREVIATION_LENGTH = 3;
    private static final String SEPARATOR = "_";
    private ResourceFileReader reader;

    public Parser(ResourceFileReader reader) {
        this.reader = reader;
    }

    public List<Racer> createRacers(String start, String end, String abbreviations) throws URISyntaxException, IOException {
        if (start == null || end == null || abbreviations == null) {
            throw new IllegalArgumentException("The input can't be null");
        }
        Map<String, LocalDateTime> startLaps = parseRacersLapTime(reader.readFile(start));
        Map<String, LocalDateTime> endLaps = parseRacersLapTime(reader.readFile(end));
        return reader.readFile(abbreviations)
                .map(line -> line.split(SEPARATOR))
                .map(array -> new Racer(array[1], array[2], Duration.between(startLaps.get(array[0]), endLaps.get(array[0]))))
                .collect(Collectors.toList());
    }

    private Map<String, LocalDateTime> parseRacersLapTime(Stream<String> times) {
        return times
                .collect(Collectors.toMap(s -> s.substring(0, ABBREVIATION_LENGTH), s -> parseTime(s.substring(ABBREVIATION_LENGTH))));
    }

    private LocalDateTime parseTime(String string) {
        return LocalDateTime.parse(string, DATE_TIME_FORMATTER);
    }
}