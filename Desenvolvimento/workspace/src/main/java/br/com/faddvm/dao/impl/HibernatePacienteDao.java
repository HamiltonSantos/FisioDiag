package br.com.faddvm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
		paciente.setIndicacao(getIndicacao(paciente));
		paciente.setHistoricoIndicacao(getHistoricoIndicacao(paciente));
		return paciente;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Paciente> lista() {

		List<Paciente> pacientes = manager.createQuery("FROM Paciente")
				.getResultList();

		return pacientes;
	}

	@Override
	public FaixaValor getIndicacao(Paciente paciente) {
		FaixaValor faixa = null;
		try {
			faixa = (FaixaValor) manager
					.createQuery(
							"From FaixaValor as f where f.variavel.id = 3 and f.valorMin <= ?1 and f.valorMax >= ?2")
					.setParameter(1, paciente.getPontos())
					.setParameter(2, paciente.getPontos()).getSingleResult();
		} catch (NoResultException ex) {

		}

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
				.createQuery(
						"From Historico h where h.paciente.id = ?1 group by h.variavel.id order by h.dataHistorico")
				.setParameter(1, paciente.getId()).getResultList();

		if (historicoIndicacao == null) {
			historicoIndicacao = new ArrayList<Historico>();
		}
		return historicoIndicacao;
	}

}
