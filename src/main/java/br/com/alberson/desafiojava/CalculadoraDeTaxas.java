package br.com.alberson.desafiojava;

import br.com.alberson.desafiojava.model.Transferencia;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public final class CalculadoraDeTaxas {

    public void calcularTaxa(final Transferencia transferencia) {
        if (transferencia.getValorTransferencia() == null) {
            throw new IllegalArgumentException("Valor da transferência não pode ser nulo");
        }
        if (BigDecimal.ZERO.compareTo(transferencia.getValorTransferencia()) > 0) {
            throw new IllegalArgumentException("Valor da transferência não pode ser negativo");
        }
        transferencia.setTaxa(transferencia.getTipoTransferencia().calcularTaxa(transferencia));
    }
}
