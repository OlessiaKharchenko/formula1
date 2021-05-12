package view;

import model.Racer;

import java.util.List;

public class FormatterDecorator implements Formatter {
    private Formatter formatter;

    public FormatterDecorator(Formatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public String format(List<Racer> racers) {
        return formatter.format(racers);
    }
}
