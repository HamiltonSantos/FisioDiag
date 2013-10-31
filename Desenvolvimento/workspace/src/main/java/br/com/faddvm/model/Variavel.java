package br.com.faddvm.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;

@Entity
public class Variavel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private String descricao;
	private Character tipo;
	private Character status;
	@ManyToOne
	private Categoria categoria;
	@OneToMany(mappedBy = "variavel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<FaixaValor> faixaValores;
	@Formula(value = "( SELECT MIN(F.VALORMIN) FROM FADDVM.FAIXAVALOR F WHERE F.VARIAVEL_ID = ID )")
	private Integer valorMin;
	@Formula(value = "( SELECT MAX(F.VALORMAX) FROM FADDVM.FAIXAVALOR F WHERE F.VARIAVEL_ID = ID )")
	private Integer valorMax;

	public Integer getValorMin() {
		return valorMin;
	}

	public void setValorMin(Integer valorMin) {
		this.valorMin = valorMin;
	}

	@Override
	public String toString() {
		return "Variavel [id=" + id + ", descricao=" + descricao + ", tipo="
				+ tipo + ", status=" + status + ", categoria=" + categoria
				+ ", faixaValores=" + faixaValores + ", valorMin=" + valorMin
				+ ", valorMax=" + valorMax + "]";
	}

	public Integer getValorMax() {
		return valorMax;
	}

	public void setValorMax(Integer valorMax) {
		this.valorMax = valorMax;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
