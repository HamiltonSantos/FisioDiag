package br.com.faddvm.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.faddvm.dao.VariavelDao;
import br.com.faddvm.model.Variavel;

@Repository
public class HibernateVariavelDao implements VariavelDao {

	@PersistenceContext
	EntityManager manager;

	@Override
	public Variavel salvar(Variavel variavel) {

		manager.persist(variavel);

		return variavel;
	}

}
