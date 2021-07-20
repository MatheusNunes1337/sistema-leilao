package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Leilao {
	private int id;
	private LocalDate dataInicio;
	private LocalTime horaInicio;
	private LocalDate dataFinal;
	private LocalTime horaFinal;
	private List<ItemLeilao> itens = new ArrayList<>();
	private boolean situacao;
	
	public Leilao() {
		super();
	}

	public Leilao(int id, LocalDate dataInicio, LocalTime horaInicio,
			List<ItemLeilao> itens) {
		super();
		this.id = id;
		this.dataInicio = dataInicio;
		this.horaInicio = horaInicio;
		this.itens = itens;
		this.situacao = true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}

	public LocalTime getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(LocalTime horaFinal) {
		this.horaFinal = horaFinal;
	}

	public List<ItemLeilao> getItens() {
		return itens;
	}

	public void setItens(List<ItemLeilao> itens) {
		this.itens = itens;
	}

	public boolean isSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

	public void iniciarLeilao() {
		this.dataInicio = LocalDate.now();
		this.horaInicio = LocalTime.now();
	}
	
	public void finalizarLeilao() {
		this.dataFinal = LocalDate.now();
		this.horaFinal = LocalTime.now();
	}

	@Override
	public String toString() {
		return "Leilao [id=" + id + ", dataInicio=" + dataInicio + ", horaInicio=" + horaInicio + ", dataFinal="
				+ dataFinal + ", horaFinal=" + horaFinal + ", itens=" + itens + ", situacao=" + situacao + "]";
	}
	
	
	
	
}
