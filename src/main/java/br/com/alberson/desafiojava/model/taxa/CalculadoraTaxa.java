package br.com.alberson.desafiojava.model.taxa;

import br.com.alberson.desafiojava.model.Transferencia;

import java.math.BigDecimal;

public interface CalculadoraTaxa {
  BigDecimal calcular(Transferencia transferencia);
}
