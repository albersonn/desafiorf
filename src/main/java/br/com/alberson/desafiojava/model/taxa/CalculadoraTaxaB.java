package br.com.alberson.desafiojava.model.taxa;

import br.com.alberson.desafiojava.model.Transferencia;

import java.math.BigDecimal;

import static java.time.temporal.ChronoUnit.DAYS;

public final class CalculadoraTaxaB implements CalculadoraTaxa {

  private static final BigDecimal ADICIONAL_B_ATE_30_DIAS = BigDecimal.TEN;
  private static final BigDecimal ADICIONAL_B_MAIS_30_DIAS = BigDecimal.valueOf(8);

  @Override
  public BigDecimal calcular(final Transferencia transferencia) {
    BigDecimal taxa = BigDecimal.ZERO;
    long diasEntre = DAYS.between(transferencia.getDataCadastro(), transferencia.getDataAgendamento());
    if (diasEntre > 30) {
      taxa = taxa.add(ADICIONAL_B_MAIS_30_DIAS);
    } else {
      taxa = taxa.add(ADICIONAL_B_ATE_30_DIAS);
    }
    return taxa;
  }
}
