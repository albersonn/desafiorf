package br.com.alberson.desafiojava.model.taxa;

import br.com.alberson.desafiojava.model.Transferencia;

import java.math.BigDecimal;

public class CalculadoraTaxaD implements CalculadoraTaxa {

  private static BigDecimal limiteA = BigDecimal.valueOf(25_000.99);
  private static BigDecimal limiteC = new BigDecimal(120_000);

  @Override
  public BigDecimal calcular(Transferencia transferencia) {
    BigDecimal valorTransferencia = transferencia.getValorTransferencia();
    if (valorTransferencia.compareTo(limiteC) > 0) {
      return new CalculadoraTaxaC().calcular(transferencia);
    } else {
      if (valorTransferencia.compareTo(limiteA) > 0) {
        return new CalculadoraTaxaB().calcular(transferencia);
      }
      return new CalculadoraTaxaA().calcular(transferencia);
    }
  }
}
