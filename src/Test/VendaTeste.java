package Test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Model.Cliente;
import Model.Endereco;
import Model.Produto;
import Model.Venda;
import Enum.Estado;
import Enum.TipoCliente;

@RunWith(Parameterized.class)
public class VendaTeste {

	public VendaTeste(
			LocalDate data,
			Cliente cliente,
			Produto[] itens,
			String metodoPagamento,
			float icms,
			float impostoMunicipal,
			float valor,
			float frete,
			float desconto,
			float expectedCashback) {
		this.data = data;
		this.cliente = cliente;
		this.itens = itens;
		this.metodoPagamento = metodoPagamento;
		this.icms = icms;
		this.impostoMunicipal = impostoMunicipal;
		this.valor = valor;
		this.frete = frete;
		this.desconto = desconto;
		this.expectedCashback = expectedCashback;
	}

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {
                LocalDate.parse("2024-06-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                new Cliente(1, TipoCliente.PRIME, new Endereco(Estado.DF, true), 5.0f),
                new Produto[]{new Produto(1, "Produto1", 100.0f, "unidade")},
                "429613XXXXXXXXXX",
                18.0f,
                0.0f,
                118.0f,
                0.0f,   
                0.0f,   
                5.0f    
            },
            {
                LocalDate.parse("2024-06-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                new Cliente(2, TipoCliente.ESPECIAL, new Endereco(Estado.SP, true), 0.0f),
                new Produto[]{new Produto(2, "Produto2", 200.0f, "unidade")},
                "1234567890123456",
                24.0f,
                24.0f,
                232.9f,
                4.9f,   
                20.0f,   
                0.0f   
            },
            {
                LocalDate.parse("2024-06-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                new Cliente(3, TipoCliente.PADRAO, new Endereco(Estado.RJ, true), 0.0f),
                new Produto[]{new Produto(3, "Produto3", 100.0f, "unidade")},
                "6543210987654321",
                12.0f,
                12.0f,
                131.0f,
                7.0f,   
                0.0f,   
                0.0f   
            }
        });
    }

    private LocalDate data;
    private Cliente cliente;
    private Produto[] itens;
    private String metodoPagamento;
    private float impostoMunicipal;
    private float icms;
    private float valor;
    private float frete;
    private float desconto;
    private float expectedCashback;


	@Test
    public void testVenda() {
        Venda venda = new Venda(data, cliente, itens, metodoPagamento);
        
        assertEquals(data, venda.getData());
        assertEquals(cliente, venda.getCliente());
        assertEquals(itens, venda.getItens());
        assertEquals(metodoPagamento, venda.getMetodoPagamento());
    }
	
	@Test
	public void calcularValorFinal() {
		Venda venda = new Venda(data, cliente, itens, metodoPagamento);
		float valorEsperado = venda.getValor();
    	assertEquals(valorEsperado, valor, 0.1f);
	}
    
    @Test
    public void calcularDesconto() {
    	Venda venda = new Venda(data, cliente, itens, metodoPagamento);
    	venda.calcularDesconto();
    	float descontoEsperado = venda.getDesconto();
    	assertEquals(descontoEsperado, desconto, 0.1f);
    }
    @Test
    public void calcularFrete() {
    	Venda venda = new Venda(data, cliente, itens, metodoPagamento);
    	venda.calcularFrete();
    	float freteEsperado = venda.getFrete();
    	assertEquals(freteEsperado, frete, 0.1f);
    }
    @Test
    public void calcularCashback() {
    	Venda venda = new Venda(data, cliente, itens, metodoPagamento);
    	venda.calcularCashback();
    	float cashbackEsperado = cliente.getCashback();
    	assertEquals(cashbackEsperado, expectedCashback, 0.1f);
    }
    @Test
    public void calcularIcms() {
    	Venda venda = new Venda(data, cliente, itens, metodoPagamento);
    	venda.calcularIcms();
    	float icmsEsperado = venda.getICMS();
    	assertEquals(icmsEsperado, icms, 0.1f);
    }
    @Test
    public void calcularImpostoMunicipal() {
    	Venda venda = new Venda(data, cliente, itens, metodoPagamento);
    	venda.calcularImpostoMunicipal();
    	float impostoEsperado = venda.getImpostoMunicipal();
    	assertEquals(impostoEsperado, impostoMunicipal, 0.1f);
    }
}
