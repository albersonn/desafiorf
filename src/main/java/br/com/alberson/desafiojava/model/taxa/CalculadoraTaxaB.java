package br.com.alberson.desafiojava.model.taxa;

import br.com.alberson.desafiojava.model.Transferencia;

import java.math.BigDecimal;
import java.time.Period;

import static java.time.temporal.ChronoUnit.DAYS;

public class CalculadoraTaxaB implements CalculadoraTaxa {

  private static BigDecimal adicionalBAte30Dias = BigDecimal.TEN;
  private static BigDecimal adicionalBMais30Dias = new BigDecimal(8);

  @Override
  public BigDecimal calcular(Transferencia transferencia) {
    BigDecimal taxa = BigDecimal.ZERO;
    long diasEntre = DAYS.between(transferencia.getDataCadastro(), transferencia.getDataAgendamento());
    if (diasEntre > 30) {
      taxa = taxa.add(adicionalBMais30Dias);
    } else {
      taxa = taxa.add(adicionalBAte30Dias);
    }
    return taxa;
  }
}
