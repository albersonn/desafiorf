package br.com.alberson.desafiojava.dao;

import br.com.alberson.desafiojava.model.Transferencia;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public final class TransferenciaDAO {

    private List<Transferencia> transferencias;

    public TransferenciaDAO() {
        transferencias = new ArrayList<>();
    }

    public void adicionar(final Transferencia transferencia) {
        this.transferencias.add(transferencia);
    }

    public List<Transferencia> listar() {
        return new ArrayList<>(transferencias);
    }
}
