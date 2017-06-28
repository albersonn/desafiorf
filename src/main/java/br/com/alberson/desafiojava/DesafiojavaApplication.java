package br.com.alberson.desafiojava;

import br.com.alberson.desafiojava.converters.LocalDateFormatter;
import br.com.alberson.desafiojava.converters.TipoTransferenciaFormatter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class DesafiojavaApplication {

  @Configuration
  public static class ApplicationFormatterRegistrar extends WebMvcConfigurerAdapter {
    @Override
    public void addFormatters(FormatterRegistry registry) {
      registry.addFormatter(new LocalDateFormatter("yyyy-MM-dd'T'HH:mm:ss.SSS"));
      registry.addFormatter(new LocalDateFormatter("yyyy-MM-dd'T'HH:mm"));
      registry.addFormatter(new LocalDateFormatter("yyyy-MM-dd"));
      registry.addFormatter(new TipoTransferenciaFormatter());
    }
  }

  public static void main(String[] args) {
    SpringApplication.run(DesafiojavaApplication.class, args);
  }
}
