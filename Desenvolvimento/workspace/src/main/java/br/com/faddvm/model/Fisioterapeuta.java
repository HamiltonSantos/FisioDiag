package br.com.faddvm.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

@Entity
public class Fisioterapeuta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@Size(min = 6, max = 250, message = "Login deve ter no minimo 6 caracteres")
	private String login;
	@Size(min = 6, max = 250, message = "senha deve ter no minimo 6 caracteres")
	private String senha;
	@Size(min = 6, max = 250, message = "Nome deve ter no minimo 6 caracteres")
	private String nome;
	@Transient
	private String contraSenha;

	public String getContraSenha() {
		return contraSenha;
	}

	public void setContraSenha(String contraSenha) {
		this.contraSenha = contraSenha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
