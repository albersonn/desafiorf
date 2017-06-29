package br.com.alberson.desafiojava.converters;


import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateFormatter implements Formatter<LocalDate> {

    private final DateTimeFormatter formatter;

    public LocalDateFormatter(final String dateFormat) {
        this.formatter = DateTimeFormatter.ofPattern(dateFormat);
    }

    @Override
    public LocalDate parse(final String text, final Locale locale) throws ParseException {
        if (text == null || text.isEmpty()) {
            return null;
        }
        return LocalDate.parse(text, formatter);
    }

    @Override
    public String print(final LocalDate object, final Locale locale) {
        return object.format(formatter);
    }
}
