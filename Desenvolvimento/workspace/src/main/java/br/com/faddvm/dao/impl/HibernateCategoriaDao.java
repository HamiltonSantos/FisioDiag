package br.com.faddvm.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.faddvm.dao.CategoriaDao;
import br.com.faddvm.model.Categoria;

@Repository
public class HibernateCategoriaDao implements CategoriaDao {

	@PersistenceContext
	EntityManager manager;

	@Override
	public Categoria salva(Categoria categoria) {
		manager.persist(categoria);

		return categoria;
	}

}
