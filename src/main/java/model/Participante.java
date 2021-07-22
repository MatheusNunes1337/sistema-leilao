package model;

import java.util.ArrayList;
import java.util.List;

public class Participante {
	private int id;
	private String nome;
	private String login;
	private String senha;
	private String email;
	private String endereco;
	private String telefone;
	private List<Participante> lances = new ArrayList<>();
	private boolean situacao;
	
	public Participante() {
		super();
	}

	public Participante(int id, String nome, String login, String senha, String email, String endereco, String telefone) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.endereco = endereco;
		this.telefone = telefone;
		this.situacao = true;
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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
	
	public boolean getSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

	@Override
	public String toString() {
		return "Participante [id=" + id + ", nome=" + nome + ", login=" + login + ", senha=" + senha + ", email="
				+ email + ", endereco=" + endereco + ", telefone=" + telefone + ", lances=" + lances + ", situacao="
				+ situacao + "]";
	}

	
	
}
