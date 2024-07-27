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
    private float icms;
    private float impostoMunicipal;
    private float valor = 0.0f;
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
    	for(int i = 0; i < itens.length; i++) {
    		valor += itens[i].getValor();
    	}
    	calcularDesconto();
        calcularFrete();
        calcularCashback();
        calcularIcms();
        calcularImpostoMunicipal();
        valor -= desconto;
        valor += frete;
        valor += icms + impostoMunicipal;
        return valor;
    }

    public float calcularDesconto() {
    	float valorItens = 0.0f;
    	for(int i = 0; i < itens.length; i++) {
    		valorItens += itens[i].getValor();
    	}
    	if (cliente.getTipo() == TipoCliente.ESPECIAL) {
    		desconto = valorItens * 0.10f;
            if (metodoPagamento.startsWith("429613")) {
            	desconto += valorItens * 0.10f;
            }
        }
        return desconto;
    }
    
    public float calcularImpostoMunicipal() {
    	float valorItens = 0.0f;
    	for(int i = 0; i < itens.length; i++) {
    		valorItens += itens[i].getValor();
    	}
    	if (cliente.getEndereco().getEstado() == Estado.DF) {
    		return 0.0f;
    	}
    	impostoMunicipal = valorItens *0.12f;
    	return impostoMunicipal;
    }
    
    public float calcularIcms() {
    	float valorItens = 0.0f;
    	for(int i = 0; i < itens.length; i++) {
    		valorItens += itens[i].getValor();
    	}
    	if (cliente.getEndereco().getEstado() == Estado.DF) {
    		icms = valorItens * 0.18f;
    	} else {
    		icms = valorItens * 0.12f;
    	}
    	return icms;
    }

    public float calcularFrete() {
        frete = 0.0f;
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
        return frete;
    }

    public float calcularCashback() {
    	float valorItens = 0.0f;
    	for(int i = 0; i < itens.length; i++) {
    		valorItens += itens[i].getValor();
    	}
        if (cliente.getTipo() == TipoCliente.PRIME) {
            float cashbackRate = metodoPagamento.startsWith("429613") ? 0.05f : 0.03f;
            float cashback = valorItens * cashbackRate;
            cliente.setCashback(cliente.getCashback() + cashback);
            return cashback;
        }
        return 0.0f;
    }
}
