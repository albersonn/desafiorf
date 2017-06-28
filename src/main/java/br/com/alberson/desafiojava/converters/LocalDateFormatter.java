package br.com.alberson.desafiojava.converters;


import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateFormatter implements Formatter<LocalDate> {

  private final DateTimeFormatter formatter;

  public LocalDateFormatter(String dateFormat) {
    this.formatter = DateTimeFormatter.ofPattern(dateFormat);
  }

  @Override
  public LocalDate parse(String text, Locale locale) throws ParseException {
    if (text == null || text.isEmpty()) {
      return null;
    }
    return LocalDate.parse(text, formatter);
  }

  @Override
  public String print(LocalDate object, Locale locale) {
    return object.format(formatter);
  }
}
