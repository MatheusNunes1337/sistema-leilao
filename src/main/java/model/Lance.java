package model;

import java.time.LocalTime;

public class Lance {
	private int id;
	private double valorLance;
	private LocalTime horaLance;
	private Participante participante;
	private ItemLeilao item;
	private boolean situacao;
	
	public Lance() {
		super();
	}

	public Lance(int id, double valorLance, LocalTime horaLance, Participante participante, ItemLeilao item) {
		super();
		this.id = id;
		this.valorLance = valorLance;
		this.horaLance = horaLance;
		this.participante = participante;
		this.item = item;
		this.situacao = true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValorLance() {
		return valorLance;
	}

	public void setValorLance(double valorLance) {
		this.valorLance = valorLance;
	}

	public LocalTime getHoraLance() {
		return horaLance;
	}

	public void setHoraLance(LocalTime horaLance) {
		this.horaLance = horaLance;
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	public ItemLeilao getItem() {
		return item;
	}

	public void setItem(ItemLeilao item) {
		this.item = item;
	}
	
	public boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

	@Override
	public String toString() {
		return "Lance [id=" + id + ", valorLance=" + valorLance + ", horaLance=" + horaLance + ", participante="
				+ participante + ", item=" + item + ", situacao=" + situacao + "]";
	}


}
