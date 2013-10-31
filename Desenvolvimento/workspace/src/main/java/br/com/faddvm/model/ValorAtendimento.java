package br.com.faddvm.model;

import java.util.Date;

public class ValorAtendimento {

	private Long faixaId;
	private Long variavelId;

	public Long getVariavelId() {
		return variavelId;
	}

	public void setVariavelId(Long variavelId) {
		this.variavelId = variavelId;
	}

	private Long valor;
	private Date dataHistorico;

	public Date getDataHistorico() {
		return dataHistorico;
	}

	public void setDataHistorico(Date dataHistorico) {
		this.dataHistorico = dataHistorico;
	}

	@Override
	public String toString() {
		return "ValorAtendimento [faixaId=" + faixaId + ", variavelId="
				+ variavelId + ", valor=" + valor + ", dataHistorico="
				+ dataHistorico + "]";
	}

	public Long getFaixaId() {
		return faixaId;
	}

	public void setFaixaId(Long faixaId) {
		this.faixaId = faixaId;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

}
