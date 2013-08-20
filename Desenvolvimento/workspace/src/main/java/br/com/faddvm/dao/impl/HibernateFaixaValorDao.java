package br.com.faddvm.dao.impl;

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

}
