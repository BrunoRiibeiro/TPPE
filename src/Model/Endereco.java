package Model;

import Enum.Estado;


public class Endereco {
	public Endereco(Estado estado, Boolean isCapital) {
		this.estado = estado;
		this.isCapital = isCapital;
	}
	
	private Estado estado;
	private Boolean isCapital;
	
	public Estado getEstado() {
		return estado;
	}
	public Boolean getCapital() {
		return isCapital;
	}
}
