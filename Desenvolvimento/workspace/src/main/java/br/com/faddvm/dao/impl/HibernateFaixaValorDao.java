package br.com.faddvm.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.faddvm.dao.FaixaValorDao;
import br.com.faddvm.model.FaixaValor;
import br.com.faddvm.model.Paciente;

@Repository
public class HibernateFaixaValorDao implements FaixaValorDao {

	@PersistenceContext
	EntityManager manager;

	@Override
	public FaixaValor salvar(FaixaValor faixaValor) {
		manager.persist(faixaValor);
		return faixaValor;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FaixaValor> listOcorrencias() {
		List<FaixaValor> ocorrencias = manager
				.createQuery("From FaixaValor as f where f.variavel.id = ?1")
				.setParameter(1, 1l).getResultList();
		return ocorrencias;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FaixaValor> listaIntercorrencias() {
		List<FaixaValor> intercorrencias = manager
				.createQuery("From FaixaValor as f where f.variavel.id = ?1")
				.setParameter(1, 2l).getResultList();
		return intercorrencias;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FaixaValor> listaIndices() {
		List<FaixaValor> indices = manager
				.createQuery("From FaixaValor as f where f.variavel.id = ?1")
				.setParameter(1, 3l).getResultList();
		return indices;
	}

	@Override
	public void remover(FaixaValor faixaValor) {
		manager.remove(get(faixaValor.getId()));
	}

	@Override
	public FaixaValor get(Long id) {
		return manager.find(FaixaValor.class, id);
	}

	@Override
	public FaixaValor getByDescricaoAndVariavel(String descricao, Long idVariavel) {
		FaixaValor faixaValor = null;
		Query query = manager.createQuery("From FaixaValor f where f.descricao = ?1"
										+ " And f.variavel.id = ?2")
										.setParameter(1, descricao).
										setParameter(2, idVariavel);

		try {
			faixaValor = (FaixaValor) query.getSingleResult();
		} catch (NoResultException ex) {

		}

		return faixaValor;
	}
}
