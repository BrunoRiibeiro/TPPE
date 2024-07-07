package Model;

public class Produto {
    //metodo construtor com codigo, descricao, valor, unidade(peça, unidade, metro, cm3, etc)
    public Produto(int codigo, String descricao, Float valor, String unidade) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
        this.unidade = unidade;
    }
    //atributos
    private int codigo;
    private String descricao;
    private Float valor;
    private String unidade;

    //metodos
    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Float getValor() {
        return valor;
    }

    public String getUnidade() {
        return unidade;
    }
    
}