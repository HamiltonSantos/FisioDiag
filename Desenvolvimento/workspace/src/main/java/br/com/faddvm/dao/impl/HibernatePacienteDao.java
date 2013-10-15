package br.com.faddvm.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.faddvm.dao.PacienteDao;
import br.com.faddvm.model.Paciente;

@Repository
public class HibernatePacienteDao implements PacienteDao {

	@PersistenceContext
	EntityManager manager;

	@Override
	public Paciente salvar(Paciente paciente) {
		if (paciente.getId() == null) {
			manager.persist(paciente);
		} else {
			manager.merge(paciente);
		}
		return get(paciente.getId());
	}

	@Override
	public Paciente get(Long id) {
		Paciente paciente = manager.find(Paciente.class, id);
		paciente.setPontos(getPontos(paciente));
		return paciente;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Paciente> lista() {

		List<Paciente> pacientes = manager.createQuery("FROM Paciente")
				.getResultList();

		for (Paciente paciente : pacientes) {
			paciente.setPontos(getPontos(paciente));
		}

		return pacientes;
	}

	@Override
	public int getPontos(Paciente paciente) {
		// Query q =
		// manager.createQuery("select sum(h.valor) from Historico as h "
		// + "join select max(hh.data) as ultimaData from Historico as hh "
		// + "where hh.paciente_id = :pid group by hh.variavel_id as hhh "
		// + "on h.data = hhh.ultimaData").setParameter("pid",
		// paciente.getId());

		Query q = manager
				.createNativeQuery(
						"select sum(h.valor) from faddvm.Historico as h inner join (select  max(data) as ultimaData from faddvm.Historico as hh where hh.paciente_id = ? group by hh.variavel_id) hhh ON h.data = hhh.ultimaData")
				.setParameter(1, paciente.getId());
		
		BigDecimal result = (BigDecimal) q.getSingleResult();
		
		if(result == null){
			result = new BigDecimal(0);
		}
		
		return result.intValue();
	}

}
