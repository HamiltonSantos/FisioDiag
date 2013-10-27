package br.com.faddvm.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Paciente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Historico> historico;
	@CPF(message="CPF inválido")
	private String cpf;
	@NotNull(message = "Selecione ao menos 1 Sexo")
	private Character sexo;
	private String numRegistro;
	private Date dataNascimento;
	@Formula(value = "(select sum(f.peso) "
			+ "from faddvm.Historico h, faddvm.Variavel v, faddvm.FaixaValor f "
			+ "where h.dataHistorico in (select max(hh.dataHistorico) from faddvm.Historico hh group by hh.variavel_id) "
			+ "and h.variavel_id = v.id " + "and v.id = f.variavel_id "
			+ "and h.valor between f.valorMin and f.valorMax "
			+ "and h.paciente_id = id)")
	private Integer pontos;
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

	public Character getSexo() {
		return sexo;
	}

	public void setSexo(Character sexo) {
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

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
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
