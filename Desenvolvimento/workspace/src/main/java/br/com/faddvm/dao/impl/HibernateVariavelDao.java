package br.com.faddvm.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.faddvm.dao.VariavelDao;
import br.com.faddvm.model.FaixaValor;
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
		Query query = manager.createQuery("delete from Variavel where id = ?1").setParameter(1, variavel.getId());
		
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FaixaValor> getFaixasByVariavel(Variavel variavel) {
		List<FaixaValor> faixas = null;

		Query query = manager.createQuery(
				"from FaixaValor f where f.variavel.id = ?1").setParameter(1,
				variavel.getId());

		faixas = query.getResultList();

		return faixas;
	}
}
