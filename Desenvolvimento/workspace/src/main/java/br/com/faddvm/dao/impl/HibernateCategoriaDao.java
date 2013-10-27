package br.com.faddvm.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.faddvm.dao.CategoriaDao;
import br.com.faddvm.model.Categoria;

@Repository
public class HibernateCategoriaDao implements CategoriaDao {

	@PersistenceContext
	EntityManager manager;

	@Override
	public Categoria salvar(Categoria categoria) {
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
		return (List<Categoria>) manager
				.createQuery("FROM Categoria as c where c.status = ?1")
				.setParameter(1, 'C').getResultList();
	}

	@Override
	public void remover(Categoria categoria) {
		manager.remove(categoria);
	}

	@Override
	public Categoria getByDescricao(String descricao) {

		Categoria categoria = null;

		Query query = manager.createQuery(
				"FROM Categoria as c where c.descricao = ?1").setParameter(1,
				descricao);

		try {
			categoria = (Categoria) query.getSingleResult();
		} catch (NoResultException ex) {

		}
		return categoria;
	}

}
