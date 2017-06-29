package br.com.alberson.desafiojava.model.taxa;

import br.com.alberson.desafiojava.model.Transferencia;

import java.math.BigDecimal;

public final class CalculadoraTaxaD implements CalculadoraTaxa {

  private static final BigDecimal LIMITE_A = BigDecimal.valueOf(25_000.99);
  private static final BigDecimal LIMITE_C = BigDecimal.valueOf(120_000);

  @Override
  public BigDecimal calcular(final Transferencia transferencia) {
    BigDecimal valorTransferencia = transferencia.getValorTransferencia();
    if (valorTransferencia.compareTo(LIMITE_C) > 0) {
      return new CalculadoraTaxaC().calcular(transferencia);
    } else {
      if (valorTransferencia.compareTo(LIMITE_A) > 0) {
        return new CalculadoraTaxaB().calcular(transferencia);
      }
      return new CalculadoraTaxaA().calcular(transferencia);
    }
  }
}
