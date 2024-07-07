package Test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Model.Produto;

@RunWith(Parameterized.class)

public class ProdutoTeste {

	private int codigo;
    private String descricao;
    private Float valor;
    private String unidade;
    
    public ProdutoTeste(int codigo, String descricao, Float valor, String unidade){
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
        this.unidade = unidade;
    }
    
    @Parameters
    public static Object[][] getParameters(){
        return new Object[][] {
            {1, "Navalha", 0.0f, "u"},
            {2, "Tesoura", 0.3f, "u"},
            {3, "Espuma", 10.5f, "asodkaosdk"}
        };
    }

   
    @Test
    public void test(){
        Produto p = new Produto(codigo, descricao, valor, unidade);
        assertEquals(codigo, p.getCodigo());
        assertEquals(descricao, p.getDescricao());
        assertEquals(valor, p.getValor());
        assertEquals(unidade, p.getUnidade());
    }
	
}
