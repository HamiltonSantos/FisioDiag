package br.com.faddvm.dao;

import java.util.List;

import br.com.faddvm.model.Paciente;

public interface PacienteDao {

	public Paciente salvar(Paciente paciente);
	
	public Paciente get(Long id);
	
	public List<Paciente> lista();
}
