package model;

import java.util.ArrayList;
import java.util.List;

public class ItemLeilao {
	private int id;
	private String titulo;
	private String descricao;
	private double lanceMinimo;
	private boolean arrematado;
	private List<Lance> lances = new ArrayList<>();
	private boolean situacao;
	
	public ItemLeilao() {
		super();
	}
	
	public ItemLeilao(int id, String titulo, String descricao, double lanceMinimo, boolean arrematado) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.lanceMinimo = lanceMinimo;
		this.arrematado = arrematado;
		this.situacao = true;
	}

	public ItemLeilao(String titulo, String descricao, double lanceMinimo, boolean arrematado) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.lanceMinimo = lanceMinimo;
		this.arrematado = arrematado;
		this.situacao = true;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void arrematarItem(boolean status) {
		this.arrematado = status;
	}

	public List<Lance> getLances() {
		return lances;
	}

	public void setLances(List<Lance> lances) {
		this.lances = lances;
	}
	
	public boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

	@Override
	public String toString() {
		return "ItemLeilao [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", lanceMinimo="
				+ lanceMinimo + ", arrematado=" + arrematado + ", lances=" + lances + ", situacao=" + situacao + "]";
	}

	

	
}
