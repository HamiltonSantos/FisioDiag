package br.com.faddvm.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.faddvm.dao.PacienteDao;
import br.com.faddvm.model.FaixaValor;
import br.com.faddvm.model.Historico;
import br.com.faddvm.model.Paciente;

@Repository
public class HibernatePacienteDao implements PacienteDao {

	@PersistenceContext
	EntityManager manager;

	@Override
	public Paciente salvar(Paciente paciente) {
		if (paciente.getId() == null) {
			manager.persist(paciente);
		} else {
			manager.merge(paciente);
		}
		return get(paciente.getId());
	}

	@Override
	public Paciente get(Long id) {
		Paciente paciente = manager.find(Paciente.class, id);
		paciente.setPontos(getPontos(paciente));
		paciente.setIndicacao(getIndicacao(paciente));
		paciente.setHistoricoIndicacao(getHistoricoIndicacao(paciente));
		return paciente;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Paciente> lista() {

		List<Paciente> pacientes = manager.createQuery("FROM Paciente")
				.getResultList();

		for (Paciente paciente : pacientes) {
			paciente.setPontos(getPontos(paciente));
		}

		return pacientes;
	}

	@Override
	public int getPontos(Paciente paciente) {
		BigDecimal result = (BigDecimal) manager
				.createNativeQuery(
						"select sum(h.valor) from faddvm.Historico as h inner join (select  max(data) as ultimaData from faddvm.Historico as hh where hh.paciente_id = ? group by hh.variavel_id) hhh ON h.data = hhh.ultimaData")
				.setParameter(1, paciente.getId()).getSingleResult();

		if (result == null) {
			result = new BigDecimal(0);
		}

		return result.intValue();
	}

	@Override
	public FaixaValor getIndicacao(Paciente paciente) {
		FaixaValor faixa = (FaixaValor) manager
				.createQuery(
						"From FaixaValor as f where f.variavel.id = 3 and f.valorMin <= ?1 and f.valorMax >= ?2")
				.setParameter(1, paciente.getPontos())
				.setParameter(2, paciente.getPontos()).getSingleResult();
		if (faixa == null) {
			faixa = new FaixaValor();
			faixa.setDescricao("Indicacao nao Encontrada");
		}
		return faixa;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Historico> getHistoricoIndicacao(Paciente paciente) {

		List<Historico> historicoIndicacao = (List<Historico>) manager
				.createQuery("From Historico h where h.paciente.id = ?1 group by h.variavel.id order by h.data").setParameter(1, paciente.getId()).getResultList();

		if (historicoIndicacao == null) {
			historicoIndicacao = new ArrayList<Historico>();
		}
		return historicoIndicacao;
	}

}
