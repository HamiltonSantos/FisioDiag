package br.com.faddvm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class FaixaValor {

	@Id
	@GeneratedValue
	private Long id;
	private int valorMin;
	private int valorMax;
	private int peso;
	private String descricao;
	@ManyToOne
	private Variavel variavel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getValorMin() {
		return valorMin;
	}

	public void setValorMin(int valorMin) {
		this.valorMin = valorMin;
	}

	public int getValorMax() {
		return valorMax;
	}

	public void setValorMax(int valorMax) {
		this.valorMax = valorMax;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Variavel getVariavel() {
		return variavel;
	}

	public void setVariavel(Variavel variavel) {
		this.variavel = variavel;
	}
}
