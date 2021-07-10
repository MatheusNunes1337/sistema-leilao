package model;

import java.util.ArrayList;
import java.util.List;

public class ItemLeilao {
	private String titulo;
	private String descricao;
	private double lanceMinimo;
	private boolean arrematado;
	private List<Lance> lances = new ArrayList<>();
	
	public ItemLeilao() {
		super();
	}

	public ItemLeilao(String titulo, String descricao, double lanceMinimo, boolean arrematado) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.lanceMinimo = lanceMinimo;
		this.arrematado = arrematado;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getLanceMinimo() {
		return lanceMinimo;
	}

	public void setLanceMinimo(double lanceMinimo) {
		this.lanceMinimo = lanceMinimo;
	}

	public boolean isArrematado() {
		return arrematado;
	}

	public void setArrematado(boolean arrematado) {
		this.arrematado = arrematado;
	}

	public List<Lance> getLances() {
		return lances;
	}

	public void setLances(List<Lance> lances) {
		this.lances = lances;
	}

	@Override
	public String toString() {
		return "ItemLeilao [titulo=" + titulo + ", descricao=" + descricao + ", lanceMinimo=" + lanceMinimo
				+ ", arrematado=" + arrematado + ", lances=" + lances + "]";
	}
	
	
	
}
