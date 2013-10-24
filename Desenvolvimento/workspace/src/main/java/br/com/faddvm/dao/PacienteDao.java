package br.com.faddvm.dao;

import java.util.List;

import br.com.faddvm.model.FaixaValor;
import br.com.faddvm.model.Historico;
import br.com.faddvm.model.Paciente;

public interface PacienteDao {

	Paciente salvar(Paciente paciente);

	Paciente get(Long id);

	List<Paciente> lista();

	FaixaValor getIndicacao(Paciente paciente);

	List<Historico> getHistoricoIndicacao(Paciente paciente);
}
