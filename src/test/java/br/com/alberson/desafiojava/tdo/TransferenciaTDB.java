package br.com.alberson.desafiojava.tdo;

import br.com.alberson.desafiojava.model.TipoTransferencia;
import br.com.alberson.desafiojava.model.Transferencia;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransferenciaTDB {

    public static Transferencia criarTransferencia(String contaOrigem, String contaDestino, BigDecimal valorTransferencia, LocalDate dataAgendamento, TipoTransferencia tipoTransferencia) {
        Transferencia transferencia = new Transferencia();
        transferencia.setContaOrigem(contaOrigem);
        transferencia.setContaDestino(contaDestino);
        transferencia.setDataAgendamento(dataAgendamento);
        transferencia.setValorTransferencia(valorTransferencia);
        transferencia.setTipoTransferencia(tipoTransferencia);
        return transferencia;
    }

    public static Transferencia criarTransferenciaZerada(String contaOrigem, String contaDestino, LocalDate dataAgendamento, TipoTransferencia tipoTransferencia) {
        return criarTransferencia(contaOrigem, contaDestino, BigDecimal.ZERO, dataAgendamento, tipoTransferencia);
    }

    public static Transferencia clonarTransferenciaNaData(Transferencia transferenciaFonte, LocalDate novaDataAgendamento) {
        Transferencia transferencia = new Transferencia();

        transferencia.setDataCadastro(transferenciaFonte.getDataCadastro());
        transferencia.setContaOrigem(transferenciaFonte.getContaOrigem());
        transferencia.setContaDestino(transferenciaFonte.getContaDestino());
        transferencia.setValorTransferencia(transferenciaFonte.getValorTransferencia());
        transferencia.setDataAgendamento(novaDataAgendamento);
        transferencia.setTipoTransferencia(transferenciaFonte.getTipoTransferencia());

        return transferencia;
    }
}
