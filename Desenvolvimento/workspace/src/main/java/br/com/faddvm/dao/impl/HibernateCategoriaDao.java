package br.com.faddvm.dao.impl;

import java.util.List;

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

	@Override
	public Categoria get(Long categoria_id) {

		return manager.find(Categoria.class, categoria_id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> lista() {
		return (List<Categoria>) manager.createQuery("FROM Categoria").getResultList();
	}

}
