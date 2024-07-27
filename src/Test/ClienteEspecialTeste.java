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
import Model.database.DataBaseProvider;
import Model.Endereco;
import Model.Produto;
import Model.Venda;
import Enum.Estado;
import Enum.TipoCliente;

@RunWith(Parameterized.class)
public class ClienteEspecialTeste {

    static {
        
        Venda venda1 = new Venda(
        	
				LocalDate.parse("2024-06-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                new Cliente(1, TipoCliente.PADRAO, new Endereco(Estado.DF, true), 5.0f),
                new Produto[]{new Produto(1, "Produto1", 105.0f, "unidade")},
                "429613XXXXXXXXXX"
        );
        Venda venda2 = new Venda(
      
				LocalDate.parse("2024-05-05", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                new Cliente(1, TipoCliente.PADRAO, new Endereco(Estado.DF, true), 5.0f),
                new Produto[]{new Produto(2, "Produto2", 200.0f, "unidade")},
                "1234567890123456"
            
        );
        Venda venda3 = new Venda(
        	
 				LocalDate.parse("2024-04-20", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                 new Cliente(1, TipoCliente.PADRAO, new Endereco(Estado.DF, true), 5.0f),
                 new Produto[]{new Produto(3, "Produto3", 150.0f, "unidade")},
                 "6543210987654321"
        );

        DataBaseProvider.getVendas().add(venda1);
        DataBaseProvider.getVendas().add(venda2);
        DataBaseProvider.getVendas().add(venda3);
    }

    @Parameters
    public static Collection<Object[]> getParameters() {
        return Arrays.asList(new Object[][]{
                {1, true},
                {2, false},
                {3, false}
        });
    }

    private int idClient;
    private boolean respostaEsperada;

    public ClienteEspecialTeste(int idClient, boolean respostaEsperada) {
        this.idClient = idClient;
        this.respostaEsperada = respostaEsperada;
    }

    @Test
    public void testIsEspecial() {
        boolean result = DataBaseProvider.isEspecial(idClient);
        assertEquals(respostaEsperada, result);
    }
}
