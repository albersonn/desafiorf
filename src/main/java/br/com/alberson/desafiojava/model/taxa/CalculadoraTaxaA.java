package br.com.alberson.desafiojava.model.taxa;

import br.com.alberson.desafiojava.model.Transferencia;

import java.math.BigDecimal;

public final class CalculadoraTaxaA implements CalculadoraTaxa {

  private static final BigDecimal ADICIONAL_A = new BigDecimal(2);
  private static final BigDecimal TAXA_A = BigDecimal.valueOf(0.03);

  @Override
  public BigDecimal calcular(final Transferencia transferencia) {
    BigDecimal taxa = BigDecimal.ZERO;
    taxa = taxa.add(ADICIONAL_A);
    taxa = taxa.add(transferencia.getValorTransferencia().multiply(TAXA_A).setScale(2, BigDecimal.ROUND_HALF_EVEN));
    return taxa;
  }
}
