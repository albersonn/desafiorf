package br.com.alberson.desafiojava;

import br.com.alberson.desafiojava.model.TipoTransferencia;
import br.com.alberson.desafiojava.model.Transferencia;
import br.com.alberson.desafiojava.tdo.TransferenciaTDB;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CaluladoraDeTaxasTest {

    private static final BigDecimal ADICIONAL_A = new BigDecimal(2);
    private static final BigDecimal TAXA_A = BigDecimal.valueOf(0.03);
    private static final BigDecimal ADICIONAL_B_ATE_30_DIAS = BigDecimal.TEN;
    private static final BigDecimal ADICIONAL_B_MAIS_30_DIAS = BigDecimal.valueOf(8);
    private static final BigDecimal TAXA_C_MAIS_30_DIAS = BigDecimal.valueOf(0.012);
    private static final BigDecimal TAXA_C_ATE_30_DIAS = BigDecimal.valueOf(0.021);
    private static final BigDecimal TAXA_C_ATE_25_DIAS = BigDecimal.valueOf(0.043);
    private static final BigDecimal TAXA_C_ATE_20_DIAS = BigDecimal.valueOf(0.054);
    private static final BigDecimal TAXA_C_ATE_15_DIAS = BigDecimal.valueOf(0.067);
    private static final BigDecimal TAXA_C_ATE_10_DIAS = BigDecimal.valueOf(0.074);
    private static final BigDecimal TAXA_C_ATE_5_DIAS = BigDecimal.valueOf(0.083);
    private static final BigDecimal LIMITE_VALOR_A = BigDecimal.valueOf(25_000.99);
    private static final BigDecimal LIMITE_VALOR_INFERIOR_B = BigDecimal.valueOf(25_001);
    private static final BigDecimal LIMITE_VALOR_SUPERIOR_B = BigDecimal.valueOf(120_000);
    private static final BigDecimal LIMITE_VALOR_C = BigDecimal.valueOf(120_000.01);
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

        BigDecimal taxa = BigDecimal.ZERO;
        taxa = taxa.add(ADICIONAL_A);
        taxa = taxa.add(valorTransferencia.multiply(TAXA_A).setScale(2, BigDecimal.ROUND_HALF_EVEN));

        assertTrue(taxa.compareTo(transferencia.getTaxa()) == 0);

        Transferencia transferenciaZerada =
                TransferenciaTDB.criarTransferenciaZerada("00000-0", "00000-1", LocalDate.now(), TipoTransferencia.A);

        calculadoraDeTaxas.calcularTaxa(transferenciaZerada);

        assertTrue(ADICIONAL_A.compareTo(transferenciaZerada.getTaxa()) == 0);
    }

    @Test
    public void deveCalcularTaxaBCorretamente() {
        LocalDate daqui30Dias = LocalDate.now().plusDays(30);
        LocalDate daqui31Dias = LocalDate.now().plusDays(31);

        BigDecimal valorTransferencia = BigDecimal.valueOf(1000);

        Transferencia transferenciaHoje =
                TransferenciaTDB.criarTransferencia("11111-1", "11111-2", valorTransferencia, LocalDate.now(), TipoTransferencia.B);

        Transferencia transferencia30dias =
                TransferenciaTDB.criarTransferencia("11111-1", "11111-2", valorTransferencia, daqui30Dias, TipoTransferencia.B);

        Transferencia transferencia31dias =
                TransferenciaTDB.criarTransferencia("11111-1", "11111-2", valorTransferencia, daqui31Dias, TipoTransferencia.B);

        calculadoraDeTaxas.calcularTaxa(transferenciaHoje);
        calculadoraDeTaxas.calcularTaxa(transferencia30dias);
        calculadoraDeTaxas.calcularTaxa(transferencia31dias);

        assertTrue(ADICIONAL_B_ATE_30_DIAS.compareTo(transferenciaHoje.getTaxa()) == 0);
        assertTrue(ADICIONAL_B_ATE_30_DIAS.compareTo(transferencia30dias.getTaxa()) == 0);
        assertTrue(ADICIONAL_B_MAIS_30_DIAS.compareTo(transferencia31dias.getTaxa()) == 0);

        Transferencia transferenciaHojeZerada =
                TransferenciaTDB.criarTransferenciaZerada("11111-1", "11111-1", LocalDate.now(), TipoTransferencia.B);

        Transferencia transferencia30DiasZerada =
                TransferenciaTDB.criarTransferenciaZerada("11111-1", "11111-1", daqui30Dias, TipoTransferencia.B);

        Transferencia transferencia31DiasZerada =
                TransferenciaTDB.criarTransferenciaZerada("11111-1", "11111-1", daqui31Dias, TipoTransferencia.B);

        calculadoraDeTaxas.calcularTaxa(transferenciaHojeZerada);
        calculadoraDeTaxas.calcularTaxa(transferencia30DiasZerada);
        calculadoraDeTaxas.calcularTaxa(transferencia31DiasZerada);

        assertTrue(ADICIONAL_B_ATE_30_DIAS.compareTo(transferenciaHojeZerada.getTaxa()) == 0);
        assertTrue(ADICIONAL_B_ATE_30_DIAS.compareTo(transferencia30DiasZerada.getTaxa()) == 0);
        assertTrue(ADICIONAL_B_MAIS_30_DIAS.compareTo(transferencia31DiasZerada.getTaxa()) == 0);

    }

    @Test
    public void deveCalcularTaxaCCorretamente() {
        List<BigDecimal> taxas = Arrays.asList(
                TAXA_C_ATE_25_DIAS,
                TAXA_C_ATE_20_DIAS,
                TAXA_C_ATE_15_DIAS,
                TAXA_C_ATE_10_DIAS,
                TAXA_C_ATE_5_DIAS
        );

        BigDecimal valorTransferencia = BigDecimal.TEN;

        LocalDate data = LocalDate.now().plusDays(31);

        Transferencia transferencia =
                TransferenciaTDB.criarTransferencia("11111-1", "11111-1", valorTransferencia, data, TipoTransferencia.C);

        Transferencia transferenciaZerada =
                TransferenciaTDB.criarTransferenciaZerada("11111-1", "11111-1", data, TipoTransferencia.C);

        calculadoraDeTaxas.calcularTaxa(transferencia);
        calculadoraDeTaxas.calcularTaxa(transferenciaZerada);

        BigDecimal taxaEsperada = valorTransferencia.multiply(TAXA_C_MAIS_30_DIAS).setScale(2, BigDecimal.ROUND_HALF_EVEN);

        assertTrue(taxaEsperada.compareTo(transferencia.getTaxa()) == 0);
        assertTrue(BigDecimal.ZERO.compareTo(transferenciaZerada.getTaxa()) == 0);

        data = data.minusDays(1);

        transferencia =
                TransferenciaTDB.criarTransferencia("11111-1", "11111-1", valorTransferencia, data, TipoTransferencia.C);

        transferenciaZerada =
                TransferenciaTDB.criarTransferenciaZerada("11111-1", "11111-1", data, TipoTransferencia.C);

        calculadoraDeTaxas.calcularTaxa(transferencia);
        calculadoraDeTaxas.calcularTaxa(transferenciaZerada);

        taxaEsperada = valorTransferencia.multiply(TAXA_C_ATE_30_DIAS).setScale(2, BigDecimal.ROUND_HALF_EVEN);

        assertTrue(taxaEsperada.compareTo(transferencia.getTaxa()) == 0);
        assertTrue(BigDecimal.ZERO.compareTo(transferenciaZerada.getTaxa()) == 0);

        for (BigDecimal taxa : taxas) {
            data = data.minusDays(5);

            transferencia =
                    TransferenciaTDB.clonarTransferenciaNaData(transferencia, data);

            transferenciaZerada =
                    TransferenciaTDB.clonarTransferenciaNaData(transferenciaZerada, data);

            calculadoraDeTaxas.calcularTaxa(transferencia);
            calculadoraDeTaxas.calcularTaxa(transferenciaZerada);

            taxaEsperada = valorTransferencia.multiply(taxa).setScale(2, BigDecimal.ROUND_HALF_EVEN);

            assertTrue(taxaEsperada.compareTo(transferencia.getTaxa()) == 0);
            assertTrue(BigDecimal.ZERO.compareTo(transferenciaZerada.getTaxa()) == 0);
        }
    }

    @Test
    public void deveCalcularTaxaDCorretamente() {


        Transferencia transferenciaA =
                TransferenciaTDB.criarTransferencia("00000-1", "00000-2", LIMITE_VALOR_A, LocalDate.now(), TipoTransferencia.D);

        Transferencia transferenciaInferiorB =
                TransferenciaTDB.criarTransferencia("00000-3", "00000-4", LIMITE_VALOR_INFERIOR_B, LocalDate.now(), TipoTransferencia.D);

        Transferencia transferenciaSuperiorB =
                TransferenciaTDB.criarTransferencia("00000-3", "00000-4", LIMITE_VALOR_SUPERIOR_B, LocalDate.now(), TipoTransferencia.D);

        Transferencia transferenciaC =
                TransferenciaTDB.criarTransferencia("00000-3", "00000-4", LIMITE_VALOR_C, LocalDate.now().plusDays(31), TipoTransferencia.D);

        calculadoraDeTaxas.calcularTaxa(transferenciaA);
        calculadoraDeTaxas.calcularTaxa(transferenciaInferiorB);
        calculadoraDeTaxas.calcularTaxa(transferenciaSuperiorB);
        calculadoraDeTaxas.calcularTaxa(transferenciaC);

        BigDecimal taxaA = BigDecimal.ZERO;
        taxaA = taxaA.add(ADICIONAL_A);
        taxaA = taxaA.add(LIMITE_VALOR_A.multiply(TAXA_A).setScale(2, BigDecimal.ROUND_HALF_EVEN));

        BigDecimal taxaC = LIMITE_VALOR_C.multiply(TAXA_C_MAIS_30_DIAS).setScale(2, BigDecimal.ROUND_HALF_EVEN);

        assertTrue(taxaA.compareTo(transferenciaA.getTaxa()) == 0);
        assertTrue(BigDecimal.TEN.compareTo(transferenciaInferiorB.getTaxa()) == 0);
        assertTrue(BigDecimal.TEN.compareTo(transferenciaSuperiorB.getTaxa()) == 0);
        assertTrue(taxaC.compareTo(transferenciaC.getTaxa()) == 0);
    }
}
