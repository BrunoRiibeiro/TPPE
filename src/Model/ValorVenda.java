package Model;

public class ValorVenda {
    private Venda venda;
    private VendaCalculator vendaCalculator;
    private float valor;

    public ValorVenda(Venda venda) {
        this.venda = venda;
        this.vendaCalculator = new VendaCalculator(venda);
    }

    public float calcularValor() {
        valor = vendaCalculator.calcularValorItens();
        float desconto = vendaCalculator.calcularDesconto();
        float frete = vendaCalculator.calcularFrete();
        float icms = vendaCalculator.calcularIcms();
        float impostoMunicipal = vendaCalculator.calcularImpostoMunicipal();
        valor -= desconto;
        valor += frete;
        valor += icms + impostoMunicipal;
        return valor;
    }
}
