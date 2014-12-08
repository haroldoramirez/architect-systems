package br.udc.edu.sistemas.entity;

public class Modelo extends Entity{
	
	private String descricao;
	private Marca marca;
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Marca getMarca() {
		return marca;
	}
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
	@Override
	public String toString() {
		return this.id + " - " + this.descricao;
	}
}
