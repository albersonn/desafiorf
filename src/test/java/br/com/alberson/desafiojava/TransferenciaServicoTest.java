package br.com.alberson.desafiojava;

import br.com.alberson.desafiojava.dao.TransferenciaDAO;
import br.com.alberson.desafiojava.model.TipoTransferencia;
import br.com.alberson.desafiojava.model.Transferencia;
import br.com.alberson.desafiojava.servico.TransferenciaServico;
import br.com.alberson.desafiojava.tdo.TransferenciaTDB;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransferenciaServicoTest {

    @Mock
    private TransferenciaDAO mockedTransferenciaDAO;

    private List<Transferencia> transferenciasMock;

    @Mock
    private CalculadoraDeTaxas calculadoraDeTaxas;

    @Before
    public void setUp() {
        transferenciasMock = new ArrayList<>();

        doAnswer(invocation -> {
            Transferencia transferenciaArg = invocation.getArgumentAt(0, Transferencia.class);
            transferenciasMock.add(transferenciaArg);
            return transferenciaArg;
        }).when(mockedTransferenciaDAO).adicionar(any(Transferencia.class));

        doReturn(transferenciasMock).when(mockedTransferenciaDAO).listar();

        Transferencia transferencia1 =
                TransferenciaTDB.criarTransferencia("00000-1", "00000-1", BigDecimal.TEN, LocalDate.now(), TipoTransferencia.A);

        Transferencia transferencia2 =
                TransferenciaTDB.criarTransferencia("00000-1", "00000-1", BigDecimal.TEN, LocalDate.now(), TipoTransferencia.B);

        Transferencia transferencia3 =
                TransferenciaTDB.criarTransferencia("00000-1", "00000-1", BigDecimal.TEN, LocalDate.now(), TipoTransferencia.C);

        Transferencia transferencia4 =
                TransferenciaTDB.criarTransferencia("00000-1", "00000-1", BigDecimal.TEN, LocalDate.now(), TipoTransferencia.D);

        doCallRealMethod().when(calculadoraDeTaxas).calcularTaxa(any(Transferencia.class));

        mockedTransferenciaDAO.adicionar(transferencia1);
        mockedTransferenciaDAO.adicionar(transferencia2);
        mockedTransferenciaDAO.adicionar(transferencia3);
        mockedTransferenciaDAO.adicionar(transferencia4);
    }

    @Test
    public void deveAdicionarUmaTransferencia() {

        TransferenciaServico transferenciaServico = new TransferenciaServico(mockedTransferenciaDAO, calculadoraDeTaxas);

        Transferencia transferencia =
                TransferenciaTDB.criarTransferencia("00000-1", "00000-1", BigDecimal.TEN, LocalDate.now(), TipoTransferencia.A);

        transferenciaServico.adicionar(transferencia);

        verify(calculadoraDeTaxas).calcularTaxa(transferencia);
        verify(mockedTransferenciaDAO).adicionar(transferencia);

        List<Transferencia> transferencias = transferenciaServico.listar();


        assertEquals(transferenciasMock.size(), transferencias.size());

        assertThat(transferencias, hasItem(transferencia));
    }

}
