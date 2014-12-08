package br.udc.edu.sistemas.entity;

public class Marca extends Entity{

	private String descricao;
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return this.id + " - " + this.descricao;
	}
}
