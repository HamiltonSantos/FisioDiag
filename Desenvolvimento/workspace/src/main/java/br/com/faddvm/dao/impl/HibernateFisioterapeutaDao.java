package br.com.faddvm.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.faddvm.dao.FisioterapeutaDao;
import br.com.faddvm.model.Fisioterapeuta;

@Repository
public class HibernateFisioterapeutaDao implements FisioterapeutaDao {

	@PersistenceContext
	EntityManager manager;

	@Override
	public Fisioterapeuta salvar(Fisioterapeuta fisioterapeuta) {

		manager.persist(fisioterapeuta);

		return fisioterapeuta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fisioterapeuta> lista() {
		// TODO Auto-generated method stub
		return (List<Fisioterapeuta>) manager
				.createQuery("From Fisioterapeuta").getResultList();
	}

	@Override
	public Fisioterapeuta validaLogin(Fisioterapeuta fisioterapeuta) {
		@SuppressWarnings("unchecked")
		List<Fisioterapeuta> lista= (List<Fisioterapeuta>) manager.createQuery(
				"from Fisioterapeuta as "
						+ "f where f.login = ?1 and f.senha = ?2")
				.setParameter(1, fisioterapeuta.getLogin())
				.setParameter(2, fisioterapeuta.getSenha()).getResultList();
		
		if(lista.size() > 0) {
			return lista.get(0);
		}
		return null;
	}
}
