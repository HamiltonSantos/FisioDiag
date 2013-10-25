package br.com.faddvm.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class FaixaValor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private Integer valorMin;
	private Integer valorMax;
	private Integer peso;
	private String descricao;
	@ManyToOne
	private Variavel variavel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getValorMin() {
		return valorMin;
	}

	public void setValorMin(Integer valorMin) {
		this.valorMin = valorMin;
	}

	public Integer getValorMax() {
		return valorMax;
	}

	public void setValorMax(Integer valorMax) {
		this.valorMax = valorMax;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
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
