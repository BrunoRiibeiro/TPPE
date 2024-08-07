package Model;

import java.time.LocalDate;

public class Venda {
    private LocalDate data;
    private Cliente cliente;
    private Produto[] itens;
    private String metodoPagamento;
    private float icms;
    private float impostoMunicipal;
    private float frete;
    private float desconto;

    public Venda(LocalDate data, Cliente cliente, Produto[] itens, String metodoPagamento) {
        this.data = data;
        this.cliente = cliente;
        this.itens = itens;
        this.metodoPagamento = metodoPagamento;
    }

    public LocalDate getData() { return data; }
    public Cliente getCliente() { return cliente; }
    public Produto[] getItens() { return itens; }
    public String getMetodoPagamento() { return metodoPagamento; }
    public float getICMS() { return icms; }
    public float getImpostoMunicipal() { return impostoMunicipal; }
    public float getFrete() { return frete; }
    public float getDesconto() { return desconto; }

    public float getValor() {
        return new ValorVenda(this).calcularValor();
    }
}
