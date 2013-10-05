package br.com.faddvm.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.faddvm.dao.FaixaValorDao;
import br.com.faddvm.model.FaixaValor;

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
}
