package Model;

import Enum.TipoCliente;

public class Cliente {
    public Cliente(int id, TipoCliente tipo, Endereco endereco, Float cashback) {
        this.id = id;
        this.tipo = tipo;
        this.endereco = endereco;
        this.cashback = cashback;
    }

    //atributos
    private int id;
    private TipoCliente tipo;
    private Endereco endereco;
    private Float cashback;

    //metodos
    public int getId() {
        return id;
    }

    public TipoCliente getTipo() {
        return tipo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Float getCashback() {
        return cashback;
    }

	public void setCashback(float f) {
		// TODO Auto-generated method stub
		
	}
	
 
    public static Cliente criarCliente(int id, TipoCliente tipo, Endereco endereco, Float cashback) {
        return new Cliente(id, tipo, endereco, cashback);
    }
    
}