package br.com.alberson.desafiojava.converters;

import br.com.alberson.desafiojava.model.TipoTransferencia;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class TipoTransferenciaFormatter implements Formatter<TipoTransferencia> {

    @Override
    public TipoTransferencia parse(String text, Locale locale) throws ParseException {
        if (text == null || text.isEmpty())
            return null;
        return TipoTransferencia.valueOf(text.toUpperCase());
    }

    @Override
    public String print(TipoTransferencia object, Locale locale) {
        return object.toString();
    }
}
