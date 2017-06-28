package br.com.alberson.desafiojava;

import br.com.alberson.desafiojava.model.TipoTransferencia;
import br.com.alberson.desafiojava.model.Transferencia;
import br.com.alberson.desafiojava.tdo.TransferenciaTDB;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.TRANSACTION_MODE;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CaluladoraDeTaxasTest {

    private CalculadoraDeTaxas calculadoraDeTaxas;

    @Before
    public void obterCalculadoraDeTaxas() {
        calculadoraDeTaxas = new CalculadoraDeTaxas();
    }

    @Test
    public void deveColocarADataDeCadastroComoHoje() {

        Transferencia transferencia =
                TransferenciaTDB.criarTransferencia("11111-1", "11111-2", BigDecimal.ONE, LocalDate.now(), TipoTransferencia.A);

        calculadoraDeTaxas.calcularTaxa(transferencia);

        assertEquals(transferencia.getDataCadastro(), LocalDate.now());
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoDeveAceitarValoresNegativos() {
        Transferencia transferencia = new Transferencia();
        transferencia.setValorTransferencia(new BigDecimal(-1f));
        calculadoraDeTaxas.calcularTaxa(transferencia);
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoDeveAceitarValorNulo() {
        Transferencia transferencia = new Transferencia();
        calculadoraDeTaxas.calcularTaxa(transferencia);
    }

    @Test
    public void deveCalcularTaxaACorretamente() {
        LocalDate amanha = LocalDate.now().plusDays(1);

        BigDecimal valorTransferencia = new BigDecimal(1000);
        Transferencia transferencia =
                TransferenciaTDB.criarTransferencia("11111-1", "11111-2", valorTransferencia, amanha, TipoTransferencia.A);

        calculadoraDeTaxas.calcularTaxa(transferencia);

        BigDecimal taxa = new BigDecimal(2);
        taxa = taxa.add(valorTransferencia.multiply(BigDecimal.valueOf(0.03)).setScale(2, BigDecimal.ROUND_HALF_EVEN));

        assertTrue(taxa.compareTo(transferencia.getTaxa()) == 0);

        Transferencia transferenciaZerada =
                TransferenciaTDB.criarTransferenciaZerada("00000-0", "00000-1", LocalDate.now(), TipoTransferencia.A);

        taxa = new BigDecimal(2);

        calculadoraDeTaxas.calcularTaxa(transferenciaZerada);

        assertTrue(taxa.compareTo(transferenciaZerada.getTaxa()) == 0);
    }

    @Test
    public void deveCalcularTaxaBCorretamente() {
        LocalDate daqui30Dias = LocalDate.now().plusDays(30);
        LocalDate daqui31Dias = LocalDate.now().plusDays(31);

        BigDecimal valorTransferencia = new BigDecimal(1000);

        Transferencia transferenciaHoje =
                TransferenciaTDB.criarTransferencia("11111-1", "11111-2", valorTransferencia, LocalDate.now(), TipoTransferencia.B);

        Transferencia transferencia30dias =
                TransferenciaTDB.criarTransferencia("11111-1", "11111-2", valorTransferencia, daqui30Dias, TipoTransferencia.B);

        Transferencia transferencia31dias =
                TransferenciaTDB.criarTransferencia("11111-1", "11111-2", valorTransferencia, daqui31Dias, TipoTransferencia.B);

        calculadoraDeTaxas.calcularTaxa(transferenciaHoje);
        calculadoraDeTaxas.calcularTaxa(transferencia30dias);
        calculadoraDeTaxas.calcularTaxa(transferencia31dias);

        BigDecimal taxaAte30Dias = BigDecimal.TEN;
        BigDecimal taxaMais30Dias = new BigDecimal(8);

        assertTrue(taxaAte30Dias.compareTo(transferenciaHoje.getTaxa()) == 0);
        assertTrue(taxaAte30Dias.compareTo(transferencia30dias.getTaxa()) == 0);
        assertTrue(taxaMais30Dias.compareTo(transferencia31dias.getTaxa()) == 0);

        Transferencia transferenciaHojeZerada =
                TransferenciaTDB.criarTransferenciaZerada("11111-1", "11111-1", LocalDate.now(), TipoTransferencia.B);

        Transferencia transferencia30DiasZerada =
                TransferenciaTDB.criarTransferenciaZerada("11111-1", "11111-1", daqui30Dias, TipoTransferencia.B);

        Transferencia transferencia31DiasZerada =
                TransferenciaTDB.criarTransferenciaZerada("11111-1", "11111-1", daqui31Dias, TipoTransferencia.B);

        calculadoraDeTaxas.calcularTaxa(transferenciaHojeZerada);
        calculadoraDeTaxas.calcularTaxa(transferencia30DiasZerada);
        calculadoraDeTaxas.calcularTaxa(transferencia31DiasZerada);

        assertTrue(taxaAte30Dias.compareTo(transferenciaHojeZerada.getTaxa()) == 0);
        assertTrue(taxaAte30Dias.compareTo(transferencia30DiasZerada.getTaxa()) == 0);
        assertTrue(taxaMais30Dias.compareTo(transferencia31DiasZerada.getTaxa()) == 0);

    }

    @Test
    public void deveCalcularTaxaCCorretamente() {

        BigDecimal taxaCMais30Dias = BigDecimal.valueOf(0.12);
        BigDecimal taxaCAte30Dias = BigDecimal.valueOf(0.21);

        List<BigDecimal> taxas = Arrays.asList(
                BigDecimal.valueOf(0.43),
                BigDecimal.valueOf(0.54),
                BigDecimal.valueOf(0.67),
                BigDecimal.valueOf(0.74),
                BigDecimal.valueOf(0.83));

        BigDecimal valorTransferencia = BigDecimal.TEN;

        LocalDate data = LocalDate.now().plusDays(31);

        Transferencia transferencia =
                TransferenciaTDB.criarTransferencia("11111-1", "11111-1", valorTransferencia, data, TipoTransferencia.C);

        Transferencia transferenciaZerada =
                TransferenciaTDB.criarTransferenciaZerada("11111-1", "11111-1", data, TipoTransferencia.C);

        calculadoraDeTaxas.calcularTaxa(transferencia);
        calculadoraDeTaxas.calcularTaxa(transferenciaZerada);

        assertTrue(taxaCMais30Dias.compareTo(transferencia.getTaxa()) == 0);
        assertTrue(BigDecimal.ZERO.compareTo(transferenciaZerada.getTaxa()) == 0);

        data = data.minusDays(1);

        transferencia =
                TransferenciaTDB.criarTransferencia("11111-1", "11111-1", valorTransferencia, data, TipoTransferencia.C);

        transferenciaZerada =
                TransferenciaTDB.criarTransferenciaZerada("11111-1", "11111-1", data, TipoTransferencia.C);

        calculadoraDeTaxas.calcularTaxa(transferencia);
        calculadoraDeTaxas.calcularTaxa(transferenciaZerada);

        assertTrue(taxaCAte30Dias.compareTo(transferencia.getTaxa()) == 0);
        assertTrue(BigDecimal.ZERO.compareTo(transferenciaZerada.getTaxa()) == 0);

        for (BigDecimal taxa : taxas) {
            data = data.minusDays(5);

            transferencia =
                    TransferenciaTDB.criarTransferencia("11111-1", "11111-1", valorTransferencia, data, TipoTransferencia.C);

            transferenciaZerada =
                    TransferenciaTDB.criarTransferenciaZerada("11111-1", "11111-1", data, TipoTransferencia.C);

            calculadoraDeTaxas.calcularTaxa(transferencia);
            calculadoraDeTaxas.calcularTaxa(transferenciaZerada);

            assertTrue(taxa.compareTo(transferencia.getTaxa()) == 0);
            assertTrue(BigDecimal.ZERO.compareTo(transferenciaZerada.getTaxa()) == 0);
        }
    }

    @Test
    public void deveCalcularTaxaDCorretamente() {
        BigDecimal limiteA = BigDecimal.valueOf(25_000.99);
        BigDecimal limiteInferiorB = BigDecimal.valueOf(25_001);
        BigDecimal limiteSuperiorB = BigDecimal.valueOf(120_000);
        BigDecimal limiteC = BigDecimal.valueOf(120_000.01);


        Transferencia transferenciaA =
                TransferenciaTDB.criarTransferencia("00000-1", "00000-2", limiteA, LocalDate.now(), TipoTransferencia.D);

        Transferencia transferenciaInferiorB =
                TransferenciaTDB.criarTransferencia("00000-3", "00000-4", limiteInferiorB, LocalDate.now(), TipoTransferencia.D);

        Transferencia transferenciaSuperiorB =
                TransferenciaTDB.criarTransferencia("00000-3", "00000-4", limiteSuperiorB, LocalDate.now(), TipoTransferencia.D);

        Transferencia transferenciaC =
                TransferenciaTDB.criarTransferencia("00000-3", "00000-4", limiteC, LocalDate.now().plusDays(31), TipoTransferencia.D);

        calculadoraDeTaxas.calcularTaxa(transferenciaA);
        calculadoraDeTaxas.calcularTaxa(transferenciaInferiorB);
        calculadoraDeTaxas.calcularTaxa(transferenciaSuperiorB);
        calculadoraDeTaxas.calcularTaxa(transferenciaC);

        BigDecimal taxaA = new BigDecimal(2);
        taxaA = taxaA.add(limiteA.multiply(BigDecimal.valueOf(0.03)).setScale(2, BigDecimal.ROUND_HALF_EVEN));

        BigDecimal taxaC = limiteC.multiply(BigDecimal.valueOf(0.012)).setScale(2, BigDecimal.ROUND_HALF_EVEN);

        assertTrue(taxaA.compareTo(transferenciaA.getTaxa()) == 0);
        assertTrue(BigDecimal.TEN.compareTo(transferenciaInferiorB.getTaxa()) == 0);
        assertTrue(BigDecimal.TEN.compareTo(transferenciaSuperiorB.getTaxa()) == 0);
        assertTrue(taxaC.compareTo(transferenciaC.getTaxa()) == 0);
    }
}
