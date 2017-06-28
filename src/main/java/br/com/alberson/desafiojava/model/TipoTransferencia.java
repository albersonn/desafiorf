package br.com.alberson.desafiojava.model;

import br.com.alberson.desafiojava.model.taxa.*;

import java.math.BigDecimal;

public enum TipoTransferencia {
  A(new CalculadoraTaxaA()),
  B(new CalculadoraTaxaB()),
  C(new CalculadoraTaxaC()),
  D(new CalculadoraTaxaD());

  public BigDecimal calcularTaxa(Transferencia transferencia) {
    return this.calculadoraTaxa.calcular(transferencia);
  }

  private CalculadoraTaxa calculadoraTaxa;

  TipoTransferencia(CalculadoraTaxa calculadoraTaxa) {
    this.calculadoraTaxa = calculadoraTaxa;
  }
}
