package view;


import util.Pair;
import model.Racer;
import util.IndexedIterator;
import util.SortedIterator;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.lang.System.lineSeparator;

public class SimpleFormatter implements Formatter {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("mm:ss.SSS");

    public String format(List<Racer> racers) {
        if (racers == null) {
            throw new IllegalArgumentException("The input can't be null");
        }
        final int maxNameLength = findMaxFieldLength(racers, Racer::getName);
        final int maxTeamNameLength = findMaxFieldLength(racers, Racer::getTeam);
        String linePattern = "%" + getNumberLength(racers.size()) + "d. " + "%-"
                + maxNameLength + "s | %-" + maxTeamNameLength + "s | %s";
        return getStream(new IndexedIterator<>(new SortedIterator<>(racers)))
                .parallel()
                .map(pair -> String.format(linePattern,
                        pair.getFirst(),
                        pair.getSecond().getName(),
                        pair.getSecond().getTeam(),
                        formatDuration(pair.getSecond().getBestLap())))
                .collect(Collectors.joining(lineSeparator()));
    }

    private int getNumberLength(int number) {
        return number == 0 ? 1 : ((int) Math.log10(number)) + 1;
    }

    private Stream<Pair<Integer, Racer>> getStream(Iterator<Pair<Integer, Racer>> iterator) {
        Spliterator<Pair<Integer, Racer>> spliterator = Spliterators.spliteratorUnknownSize(iterator, 0);
        return StreamSupport.stream(spliterator, false);
    }

    private int findMaxFieldLength(List<Racer> racers, Function<Racer, String> getter) {
        return racers.stream()
                .map(getter)
                .mapToInt(String::length)
                .max()
                .orElse(0);
    }

    private String formatDuration(Duration duration) {
        return String.format(TIME_FORMATTER.format(LocalTime.MIDNIGHT.plus(duration)));
    }
}