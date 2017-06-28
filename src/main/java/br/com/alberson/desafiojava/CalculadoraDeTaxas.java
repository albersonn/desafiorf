package br.com.alberson.desafiojava;

import br.com.alberson.desafiojava.model.Transferencia;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class CalculadoraDeTaxas {

    public void calcularTaxa(Transferencia transferencia) {
        if (transferencia.getValorTransferencia() == null)
            throw new IllegalArgumentException("Valor da transferência não pode ser nulo");
        if (BigDecimal.ZERO.compareTo(transferencia.getValorTransferencia()) > 0)
            throw new IllegalArgumentException("Valor da transferência não pode ser negativo");
        transferencia.setDataCadastro(LocalDate.now());
        transferencia.setTaxa(transferencia.getTipoTransferencia().calcularTaxa(transferencia));
    }
}
