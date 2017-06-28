package br.com.alberson.desafiojava.model.taxa;

import br.com.alberson.desafiojava.model.Transferencia;

import java.math.BigDecimal;
import java.time.Period;

import static java.time.temporal.ChronoUnit.DAYS;

public class CalculadoraTaxaC implements CalculadoraTaxa {

  private static BigDecimal taxaCMais30Dias = BigDecimal.valueOf(0.012);
  private static BigDecimal taxaCAte30Dias = BigDecimal.valueOf(0.021);
  private static BigDecimal taxaCAte25Dias = BigDecimal.valueOf(0.043);
  private static BigDecimal taxaCAte20Dias = BigDecimal.valueOf(0.054);
  private static BigDecimal taxaCAte15Dias = BigDecimal.valueOf(0.067);
  private static BigDecimal taxaCAte10Dias = BigDecimal.valueOf(0.074);
  private static BigDecimal taxaCAte5Dias = BigDecimal.valueOf(0.083);

  @Override
  public BigDecimal calcular(Transferencia transferencia) {
    BigDecimal taxa = BigDecimal.ZERO;
    BigDecimal percentual;
    BigDecimal valorTransferencia = transferencia.getValorTransferencia();
    long diasEntre = DAYS.between(transferencia.getDataCadastro(), transferencia.getDataAgendamento());
    if (diasEntre > 30) {
      percentual = valorTransferencia.multiply(taxaCMais30Dias);
    } else if (diasEntre > 25) {
      percentual = valorTransferencia.multiply(taxaCAte30Dias);
    } else if (diasEntre > 20) {
      percentual = valorTransferencia.multiply(taxaCAte25Dias);
    } else if (diasEntre > 15) {
      percentual = valorTransferencia.multiply(taxaCAte20Dias);
    } else if (diasEntre > 10) {
      percentual = valorTransferencia.multiply(taxaCAte15Dias);
    } else if (diasEntre > 5) {
      percentual = valorTransferencia.multiply(taxaCAte10Dias);
    } else {
      percentual = valorTransferencia.multiply(taxaCAte5Dias);
    }
    taxa = taxa.add(percentual.setScale(2, BigDecimal.ROUND_HALF_EVEN));
    return taxa;
  }
}
