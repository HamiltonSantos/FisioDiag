package br.com.faddvm.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.faddvm.dao.VariavelDao;
import br.com.faddvm.model.Variavel;

@Transactional
@Repository
public class HibernateVariavelDao implements VariavelDao {

	@PersistenceContext
	EntityManager manager;

	@Override
	public Variavel salvar(Variavel variavel) {

		manager.persist(variavel);
		return get(variavel.getId());
	}

	@Override
	public Variavel get(Long long1) {

		Variavel variavel = manager.find(Variavel.class, long1);
		return variavel;
	}

	@Override
	public void remove(Variavel variavel) {
		Variavel variavelDeletar = get(variavel.getId());
		manager.remove(variavelDeletar);
		manager.flush();
	}
}
