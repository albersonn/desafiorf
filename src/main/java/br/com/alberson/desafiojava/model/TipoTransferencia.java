package br.com.alberson.desafiojava.model;

import br.com.alberson.desafiojava.model.taxa.*;

import java.math.BigDecimal;

public enum TipoTransferencia {
    A(new CalculadoraTaxaA()),
    B(new CalculadoraTaxaB()),
    C(new CalculadoraTaxaC()),
    D(new CalculadoraTaxaD());

    private CalculadoraTaxa calculadoraTaxa;

    TipoTransferencia(final CalculadoraTaxa calculadoraTaxa) {
        this.calculadoraTaxa = calculadoraTaxa;
    }

    public BigDecimal calcularTaxa(final Transferencia transferencia) {
        return this.calculadoraTaxa.calcular(transferencia);
    }
}
