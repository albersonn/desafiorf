package br.com.alberson.desafiojava.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

public final class Transferencia {

    @NotNull
    @Pattern(regexp = "[0-9]{5}-[0-9]", message = "{conta.formato}")
    private String contaOrigem;

    @NotNull
    @Pattern(regexp = "[0-9]{5}-[0-9]", message = "{conta.formato}")
    private String contaDestino;

    @NotNull
    private BigDecimal valorTransferencia;

    private BigDecimal taxa;

    @NotNull
    private LocalDate dataAgendamento;

    private LocalDate dataCadastro;

    @NotNull
    private TipoTransferencia tipoTransferencia;

    public Transferencia() {
        this.dataCadastro = LocalDate.now();
    }

    public String getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(String contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public String getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(String contaDestino) {
        this.contaDestino = contaDestino;
    }

    public BigDecimal getValorTransferencia() {
        return valorTransferencia;
    }

    public void setValorTransferencia(BigDecimal valorTransferencia) {
        this.valorTransferencia = valorTransferencia;
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDate dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public TipoTransferencia getTipoTransferencia() {
        return tipoTransferencia;
    }

    public void setTipoTransferencia(TipoTransferencia tipoTransferencia) {
        this.tipoTransferencia = tipoTransferencia;
    }
}
