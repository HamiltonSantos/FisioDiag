package br.com.faddvm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Historico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private Fisioterapeuta fisioterapeuta;
	@ManyToOne
	private Paciente paciente;
	private Long valor;
	@ManyToOne
	private FaixaValor faixa;

	public FaixaValor getFaixa() {
		return faixa;
	}

	public void setFaixa(FaixaValor faixa) {
		this.faixa = faixa;
	}

	private Date dataHistorico;

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Historico [id=" + id + ", fisioterapeuta=" + fisioterapeuta
				+ ", paciente=" + paciente + ", valor=" + valor + ", faixa="
				+ faixa + ", dataHistorico=" + dataHistorico + "]";
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Fisioterapeuta getFisioterapeuta() {
		return fisioterapeuta;
	}

	public void setFisioterapeuta(Fisioterapeuta fisioterapeuta) {
		this.fisioterapeuta = fisioterapeuta;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Date getDataHistorico() {
		return dataHistorico;
	}

	public void setDataHistorico(Date data) {
		this.dataHistorico = data;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}
}
