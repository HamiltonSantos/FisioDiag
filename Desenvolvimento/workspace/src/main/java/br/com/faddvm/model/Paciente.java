package br.com.faddvm.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Paciente {

	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Historico> historico;
	@CPF
	private String cpf;
	private char sexo;
	private String numRegistro;
	private Date dataNascimento;
	@Transient
	private int pontos;
	@Transient
	private FaixaValor indicacao;
	@Transient
	private List<Historico> historicoIndicacao;

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getNumRegistro() {
		return numRegistro;
	}

	public void setNumRegistro(String numRegistro) {
		this.numRegistro = numRegistro;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Historico> getHistorico() {
		return historico;
	}

	public void setHistorico(List<Historico> historico) {
		this.historico = historico;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public FaixaValor getIndicacao() {
		return indicacao;
	}

	public void setIndicacao(FaixaValor indicacao) {
		this.indicacao = indicacao;
	}

	public List<Historico> getHistoricoIndicacao() {
		return historicoIndicacao;
	}

	public void setHistoricoIndicacao(List<Historico> historicoIndicacao) {
		this.historicoIndicacao = historicoIndicacao;
	}
}
