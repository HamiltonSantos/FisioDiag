package br.com.faddvm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
		if (paciente == null) {
			return null;
		}
		paciente.setIndicacao(indicacaoPaciente(paciente));
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Historico> getHistoricoIndicacao(Paciente paciente) {

		List<Historico> historicoIndicacao = (List<Historico>) manager
				.createQuery(
						"select h "
								+ "from Historico h, Variavel v, FaixaValor f "
								+ "where h.dataHistorico in (select max(hh.dataHistorico) from Historico hh group by hh.variavel.id) "
								+ "and h.variavel.id = v.id "
								+ "and v.id = f.variavel.id "
								+ "and h.valor between f.valorMin and f.valorMax "
								+ "and h.paciente.id = ?1 "
								+ "order by f.peso desc ")
				.setParameter(1, paciente.getId()).getResultList();

		if (historicoIndicacao == null) {
			historicoIndicacao = new ArrayList<Historico>();
		}
		return historicoIndicacao;
	}

	private FaixaValor indicacaoPaciente(Paciente paciente) {
		FaixaValor faixa = null;
		Query query = manager
				.createQuery(
						"From FaixaValor f "
								+ "where ?1 between f.valorMin and f.valorMax and f.variavel.id = 3")
				.setParameter(1, paciente.getPontos());

		try {
			faixa = (FaixaValor) query.getSingleResult();
		} catch (NoResultException ex) {
			faixa = new FaixaValor();
			faixa.setDescricao("Indicação não encontrada");
		}
		return faixa;
	}

	@Override
	public Paciente getByCPF(String cpf) {
		Paciente paciente = null;
		Query query = manager.createQuery("From Paciente p where p.cpf = ?1")
				.setParameter(1, cpf);

		try {
			paciente = (Paciente) query.getSingleResult();
		} catch (NoResultException ex) {

		}

		return paciente;
	}

	@Override
	public Paciente getByNumRegistro(String numRegistro) {
		Paciente paciente = null;
		Query query = manager.createQuery(
				"From Paciente p where p.numRegistro = ?1").setParameter(1,
				numRegistro);

		try {
			paciente = (Paciente) query.getSingleResult();
		} catch (NoResultException ex) {

		}

		return paciente;
	}

}
