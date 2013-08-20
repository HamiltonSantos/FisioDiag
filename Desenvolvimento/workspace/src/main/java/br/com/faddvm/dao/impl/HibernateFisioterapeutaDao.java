package br.com.faddvm.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.faddvm.dao.FisioterapeutaDao;
import br.com.faddvm.model.Fisioterapeuta;

@Repository
public class HibernateFisioterapeutaDao implements FisioterapeutaDao{

	@PersistenceContext
	EntityManager manager;
	
	@Override
	public Fisioterapeuta salvar(Fisioterapeuta fisioterapeuta) {
		
		manager.persist(fisioterapeuta);
		
		return fisioterapeuta;
	}

}
