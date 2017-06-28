package br.com.alberson.desafiojava.model.taxa;

import br.com.alberson.desafiojava.model.Transferencia;

import java.math.BigDecimal;

public class CalculadoraTaxaA implements CalculadoraTaxa {

  private static BigDecimal adicionalA = new BigDecimal(2);
  private static BigDecimal taxaA = BigDecimal.valueOf(0.03);

  @Override
  public BigDecimal calcular(Transferencia transferencia) {
    BigDecimal taxa = BigDecimal.ZERO;
    taxa = taxa.add(adicionalA);
    taxa = taxa.add(transferencia.getValorTransferencia().multiply(taxaA).setScale(2, BigDecimal.ROUND_HALF_EVEN));
    return taxa;
  }
}
