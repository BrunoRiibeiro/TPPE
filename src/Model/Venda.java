package Model;

import Enum.Estado;
import Enum.Regiao;
import Enum.TipoCliente;
import java.time.LocalDate;

public class Venda {
    private LocalDate data;
    private Cliente cliente;
    private Produto[] itens;
    private String metodoPagamento;
    private Boolean cashbackIsUsed;
    private float imposto;
    private float valor;
    private float frete;
    private float desconto;

    public Venda(LocalDate data, Cliente cliente, Produto[] itens, String metodoPagamento, Boolean cashbackIsUsed, float imposto, float valor, float frete, float desconto) {
        this.data = data;
        this.cliente = cliente;
        this.itens = itens;
        this.metodoPagamento = metodoPagamento;
        this.cashbackIsUsed = cashbackIsUsed;
        this.imposto = imposto;
        this.valor = valor;
        this.frete = frete;
        this.desconto = desconto;
    }

    public LocalDate getData() { return data; }
    public Cliente getCliente() { return cliente; }
    public Produto[] getItens() { return itens; }
    public String getMetodoPagamento() { return metodoPagamento; }
    public Boolean getCashbackIsUsed() { return cashbackIsUsed; }
    public float getImposto() { return imposto; }
    public float getValor() { return valor; }
    public float getFrete() { return frete; }
    public float getDesconto() { return desconto; }

    public void calcularTotais() {
        calcularDesconto();
        calcularFrete();
        calcularCashback();
    }

    private void calcularDesconto() {
        if (cliente.getTipo() == TipoCliente.ESPECIAL) {
            desconto = valor * 0.10f;
            valor -= desconto;
            if (metodoPagamento.startsWith("429613")) {
                float extraDesconto = valor * 0.10f;
                desconto += extraDesconto;
                valor -= extraDesconto;
            }
        }
    }

    private void calcularFrete() {
        Estado estado = cliente.getEndereco().getEstado();
        boolean isCapital = cliente.getEndereco().getCapital();
        Regiao regiao = estado.getRegiao();

        switch (regiao) {
            case CENTRO_OESTE:
                frete = isCapital ? 10.0f : 13.0f;
                break;
            case NORDESTE:
                frete = isCapital ? 15.0f : 18.0f;
                break;
            case NORTE:
                frete = isCapital ? 20.0f : 25.0f;
                break;
            case SUDESTE:
                frete = isCapital ? 7.0f : 10.0f;
                break;
            case SUL:
                frete = isCapital ? 10.0f : 13.0f;
                break;
            default:
                frete = isCapital ? 5.0f : 0.0f;
                break;
        }

        if (cliente.getTipo() == TipoCliente.ESPECIAL) {
            frete *= 0.70f;
        } else if (cliente.getTipo() == TipoCliente.PRIME) {
            frete = 0.0f;
        }
    }

    private void calcularCashback() {
        if (cliente.getTipo() == TipoCliente.PRIME) {
            float cashbackRate = metodoPagamento.startsWith("429613") ? 0.05f : 0.03f;
            float cashback = valor * cashbackRate;
            cliente.setCashback(cliente.getCashback() + cashback);
        }
    }
}
