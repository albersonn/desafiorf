package br.com.alberson.desafiojava.dao;

import br.com.alberson.desafiojava.CalculadoraDeTaxas;
import br.com.alberson.desafiojava.model.TipoTransferencia;
import br.com.alberson.desafiojava.model.Transferencia;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransferenciaDAO {

  private List<Transferencia> transferencias;

  public TransferenciaDAO() {
    transferencias = new ArrayList<>();
  }

  public void adicionar(Transferencia transferencia) {
    this.transferencias.add(transferencia);
  }

  public List<Transferencia> listar() {
    return new ArrayList<>(transferencias);
  }
}
