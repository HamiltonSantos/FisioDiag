package br.com.faddvm.dao;

import java.util.List;

import br.com.faddvm.model.FaixaValor;
import br.com.faddvm.model.Historico;
import br.com.faddvm.model.Paciente;

public interface PacienteDao {

	Paciente salvar(Paciente paciente);

	Paciente get(Long id);

	List<Paciente> lista();

	List<Historico> getHistoricoIndicacao(Paciente paciente);

	FaixaValor getIndicacaoPaciente(Paciente paciente);

	Paciente getByCPF(String cpf);

	Paciente getByNumRegistro(String numRegistro);

	Historico getEntradaUTIRecente(Paciente paciente);

	Historico getEntradaSaidaRecente(Paciente paciente);

	Historico getEntradaVMRecente(Paciente paciente);

	Historico getExtubacaoRecente(Paciente paciente);

	Historico getDesmameRecente(Paciente paciente);

	Historico getReintubacaoRecente(Paciente paciente);
	
	Historico getMorreu(Paciente paciente);
}
