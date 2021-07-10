package model;

import java.util.ArrayList;
import java.util.List;

public class Participante {
	private int id;
	private String nome;
	private String login;
	private String senha;
	private String endereco;
	private String telefone;
	private List<Participante> lances = new ArrayList<>();
	
	public Participante() {
		super();
	}

	public Participante(int id, String nome, String login, String senha, String endereco, String telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.endereco = endereco;
		this.telefone = telefone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Participante> getLances() {
		return lances;
	}

	public void setLances(List<Participante> lances) {
		this.lances = lances;
	}

	@Override
	public String toString() {
		return "Participante [id=" + id + ", nome=" + nome + ", login=" + login + ", senha=" + senha + ", endereco="
				+ endereco + ", telefone=" + telefone + ", lances=" + lances + "]";
	}
	
}
