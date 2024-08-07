package Model;

import Enum.Estado;
import Enum.Regiao;
import Enum.TipoCliente;

public class VendaCalculator {
    private Venda venda;

    public VendaCalculator(Venda venda) {
        this.venda = venda;
    }

    public float calcularValorItens() {
        float valorItens = 0.0f;
        for (Produto item : venda.getItens())
            valorItens += item.getValor();
        return valorItens;
    }

    public float calcularDesconto() {
        float valorItens = calcularValorItens();
        float desconto = 0.0f;
        if (venda.getCliente().getTipo() == TipoCliente.ESPECIAL) {
            desconto = valorItens * 0.10f;
            if (venda.getMetodoPagamento().startsWith("429613"))
                desconto += valorItens * 0.10f;
        }
        return desconto;
    }

    public float calcularImpostoMunicipal() {
        float valorItens = calcularValorItens();
        if (venda.getCliente().getEndereco().getEstado() == Estado.DF)
            return 0.0f;
        return valorItens * 0.12f;
    }

    public float calcularIcms() {
        float valorItens = calcularValorItens();
        if (venda.getCliente().getEndereco().getEstado() == Estado.DF)
            return valorItens * 0.18f;
        else
            return valorItens * 0.12f;
    }

    public float calcularFrete() {
        Estado estado = venda.getCliente().getEndereco().getEstado();
        boolean isCapital = venda.getCliente().getEndereco().getCapital();
        Regiao regiao = estado.getRegiao();
        float frete = 0.0f;

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

        if (venda.getCliente().getTipo() == TipoCliente.ESPECIAL)
            frete *= 0.70f;
        else if (venda.getCliente().getTipo() == TipoCliente.PRIME)
            frete = 0.0f;
        return frete;
    }

    public float calcularCashback() {
        float valorItens = calcularValorItens();
        if (venda.getCliente().getTipo() == TipoCliente.PRIME) {
            float cashbackRate = venda.getMetodoPagamento().startsWith("429613") ? 0.05f : 0.03f;
            float cashback = valorItens * cashbackRate;
            venda.getCliente().setCashback(venda.getCliente().getCashback() + cashback);
            return cashback;
        }
        return 0.0f;
    }
}
