package br.udc.edu.sistemas.entity;

public class Veiculo extends Entity{
	
	private String chassis;
	private String cor;
	private String placa;
	private Modelo modelo;
	
	
	public String getChassis() {
		return chassis;
	}
	public void setChassis(String chassis) {
		this.chassis = chassis;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Modelo getModelo() {
		return modelo;
	}
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	
	@Override
	public String toString() {
		return this.id + " - " + this.placa + ((this.modelo!=null) ? " - " + this.modelo : "");
	}
}
