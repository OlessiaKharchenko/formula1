package view;

import model.Racer;

import java.util.List;

import static java.lang.System.lineSeparator;

public class SeparatorFormatter extends FormatterDecorator {
    private static final String HYPHEN = "-";
    private int lineIndex;

    public SeparatorFormatter(Formatter formatter, int lineIndex) {
        super(formatter);
        if (lineIndex < 0) {
            throw new IllegalArgumentException("Index can't be less than zero");
        }
        this.lineIndex = lineIndex;
    }

    @Override
    public String format(List<Racer> racers) {
        String string = super.format(racers);
        int blockLength = string.length() / racers.size();
        if (lineIndex > racers.size()) {
            return string;
        }
        return new StringBuilder(string)
                .insert(lineIndex * blockLength, lineSeparator() + HYPHEN.repeat(blockLength - 1)).toString();
    }
}