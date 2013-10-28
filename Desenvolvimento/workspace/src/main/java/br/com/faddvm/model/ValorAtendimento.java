package br.com.faddvm.model;

import java.util.Date;

public class ValorAtendimento {

	private Long variavelId;
	private Long valor;
	private Date dataHistorico;

	public Date getDataHistorico() {
		return dataHistorico;
	}

	public void setDataHistorico(Date dataHistorico) {
		this.dataHistorico = dataHistorico;
	}

	public Long getVariavelId() {
		return variavelId;
	}

	public void setVariavelId(Long variavelId) {
		this.variavelId = variavelId;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

}
