package br.com.faddvm.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Variavel {

	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private Character tipo;
	private Character status;
	@ManyToOne
	private Categoria categoria;
	@OneToMany(mappedBy = "variavel")
	private List<FaixaValor> faixaValores;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Character getTipo() {
		return tipo;
	}

	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<FaixaValor> getFaixaValores() {
		return faixaValores;
	}

	public void setFaixaValores(List<FaixaValor> faixaValores) {
		this.faixaValores = faixaValores;
	}

}
