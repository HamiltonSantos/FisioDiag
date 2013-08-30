package br.com.faddvm.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Paciente {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Size(min=1, max=100,message="Nome deve ser preenchido")
	private String nome;
	
	@NotNull
	@CPF
	private String cpf;
	@NotNull
	private char sexo;
	@NotNull
	@Size(min=1, message="Favor preencher o numero do registro")
	private String numRegistro;
	@NotNull(message="Data deve ser preenchida")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Pattern(regexp="/^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$/", message="data em formato invalido, deve ser (dd/MM/yyyy)")
	@Past(message="Data de nascimento deve estar no passado")
	private Date dataNascimento;

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

}
