package br.com.alberson.desafiojava.servico;

import br.com.alberson.desafiojava.CalculadoraDeTaxas;
import br.com.alberson.desafiojava.dao.TransferenciaDAO;
import br.com.alberson.desafiojava.model.Transferencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferenciaServico {

  private final TransferenciaDAO transferenciaDAO;

  private final CalculadoraDeTaxas calculadoraDeTaxas;

  @Autowired
  public TransferenciaServico(TransferenciaDAO transferenciaDAO, CalculadoraDeTaxas calculadoraDeTaxas) {
    this.transferenciaDAO = transferenciaDAO;
    this.calculadoraDeTaxas = calculadoraDeTaxas;
  }

  public List<Transferencia> listar() {
    return transferenciaDAO.listar();
  }

  public void adicionar(Transferencia transferencia) {
    calculadoraDeTaxas.calcularTaxa(transferencia);

    transferenciaDAO.adicionar(transferencia);
  }
}
