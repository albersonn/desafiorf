package br.com.alberson.desafiojava.model.taxa;

import br.com.alberson.desafiojava.model.Transferencia;

import java.math.BigDecimal;

import static java.time.temporal.ChronoUnit.DAYS;

public final class CalculadoraTaxaC implements CalculadoraTaxa {

  private static final BigDecimal TAXA_C_MAIS_30_DIAS = BigDecimal.valueOf(0.012);
  private static final BigDecimal TAXA_C_ATE_30_DIAS = BigDecimal.valueOf(0.021);
  private static final BigDecimal TAXA_C_ATE_25_DIAS = BigDecimal.valueOf(0.043);
  private static final BigDecimal TAXA_C_ATE_20_DIAS = BigDecimal.valueOf(0.054);
  private static final BigDecimal TAXA_C_ATE_15_DIAS = BigDecimal.valueOf(0.067);
  private static final BigDecimal TAXA_C_ATE_10_DIAS = BigDecimal.valueOf(0.074);
  private static final BigDecimal TAXA_C_ATE_5_DIAS = BigDecimal.valueOf(0.083);

  @Override
  public BigDecimal calcular(final Transferencia transferencia) {
    BigDecimal taxa = BigDecimal.ZERO;
    BigDecimal percentual;
    BigDecimal valorTransferencia = transferencia.getValorTransferencia();
    long diasEntre = DAYS.between(transferencia.getDataCadastro(), transferencia.getDataAgendamento());
    if (diasEntre > 30) {
      percentual = valorTransferencia.multiply(TAXA_C_MAIS_30_DIAS);
    } else if (diasEntre > 25) {
      percentual = valorTransferencia.multiply(TAXA_C_ATE_30_DIAS);
    } else if (diasEntre > 20) {
      percentual = valorTransferencia.multiply(TAXA_C_ATE_25_DIAS);
    } else if (diasEntre > 15) {
      percentual = valorTransferencia.multiply(TAXA_C_ATE_20_DIAS);
    } else if (diasEntre > 10) {
      percentual = valorTransferencia.multiply(TAXA_C_ATE_15_DIAS);
    } else if (diasEntre > 5) {
      percentual = valorTransferencia.multiply(TAXA_C_ATE_10_DIAS);
    } else {
      percentual = valorTransferencia.multiply(TAXA_C_ATE_5_DIAS);
    }
    taxa = taxa.add(percentual.setScale(2, BigDecimal.ROUND_HALF_EVEN));
    return taxa;
  }
}
