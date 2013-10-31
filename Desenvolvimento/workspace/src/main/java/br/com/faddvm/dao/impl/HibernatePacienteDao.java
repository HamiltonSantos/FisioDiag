package br.com.faddvm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.faddvm.dao.PacienteDao;
import br.com.faddvm.model.FaixaValor;
import br.com.faddvm.model.Historico;
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
		if (paciente == null) {
			return null;
		}
		return paciente;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Paciente> lista() {

		List<Paciente> pacientes = manager.createQuery("FROM Paciente")
				.getResultList();

		return pacientes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Historico> getHistoricoIndicacao(Paciente paciente) {

		List<Historico> historicoIndicacao = null;

		String sqlQuery = "select h.*, f.peso "
				+ "from faddvm.Historico h, faddvm.FaixaValor f "
				+ "where h.dataHistorico in (select max(hh.dataHistorico) from faddvm.Historico hh, faddvm.FaixaValor ff, faddvm.Variavel vv "
				+ "where hh.paciente_id = ?1 and hh.faixa_id = ff.id and vv.id != 1 and vv.id != 2 and ff.variavel_id = vv.id group by vv.id) "
				+ "and h.faixa_id = f.id "
				+ "and h.valor between f.valorMin and f.valorMax "
				+ "and h.paciente_id = ?1 " + "order by f.peso desc";

		historicoIndicacao = manager
				.createNativeQuery(sqlQuery, Historico.class)
				.setParameter(1, paciente.getId()).getResultList();

		if (historicoIndicacao == null) {
			historicoIndicacao = new ArrayList<Historico>();
		}
		return historicoIndicacao;
	}

	public FaixaValor getIndicacaoPaciente(Paciente paciente) {
		FaixaValor faixa = null;
		Query query = manager
				.createQuery(
						"From FaixaValor f "
								+ "where ?1 between f.valorMin and f.valorMax and f.variavel.id = 3")
				.setParameter(1, paciente.getPontos());

		try {
			faixa = (FaixaValor) query.getSingleResult();
		} catch (NoResultException ex) {
			faixa = new FaixaValor();
			faixa.setDescricao("Indicação não encontrada");
		}
		return faixa;
	}

	@Override
	public Paciente getByCPF(String cpf) {
		Paciente paciente = null;
		Query query = manager.createQuery("From Paciente p where p.cpf = ?1")
				.setParameter(1, cpf);

		try {
			paciente = (Paciente) query.getSingleResult();
		} catch (NoResultException ex) {

		}

		return paciente;
	}

	@Override
	public Paciente getByNumRegistro(String numRegistro) {
		Paciente paciente = null;
		Query query = manager.createQuery(
				"From Paciente p where p.numRegistro = ?1").setParameter(1,
				numRegistro);

		try {
			paciente = (Paciente) query.getSingleResult();
		} catch (NoResultException ex) {

		}

		return paciente;
	}

	@Override
	public Historico getEntradaUTIRecente(Paciente paciente) {
		Historico historico = null;

		Query query = manager
				.createQuery(
						"From Historico h "
								+ "where h.faixa.id = 1 and h.paciente.id = ?1 "
								+ "and h.dataHistorico = (select max(hh.dataHistorico) from Historico hh where hh.paciente.id = ?1 and hh.faixa.id = 1)")
				.setParameter(1, paciente.getId());

		try {
			historico = (Historico) query.getSingleResult();
		} catch (NoResultException ex) {

		}

		return historico;
	}

	@Override
	public Historico getEntradaSaidaRecente(Paciente paciente) {
		Historico historico = null;

		Query query = manager
				.createQuery(
						"From Historico h "
								+ "where h.paciente.id = ?1 "
								+ "and h.dataHistorico = (select max(hh.dataHistorico) from Historico hh where hh.paciente.id = ?1 and (hh.faixa.id = 1 or hh.faixa.id = 2))")
				.setParameter(1, paciente.getId());

		try {
			historico = (Historico) query.getSingleResult();
		} catch (NoResultException ex) {

		}

		return historico;
	}

	@Override
	public Historico getEntradaVMRecente(Paciente paciente) {
		Historico historico = null;

		Query query = manager
				.createQuery(
						"From Historico h "
								+ "where h.faixa.id = 3 and h.paciente.id = ?1 "
								+ "and h.dataHistorico >= (select max(hh.dataHistorico) from Historico hh where hh.faixa.id = 1 and hh.paciente.id = ?1) "
								+ "order by h.dataHistorico desc")
				.setParameter(1, paciente.getId());

		try {
			historico = (Historico) query.getSingleResult();
		} catch (NoResultException ex) {

		} catch (NonUniqueResultException ex) {
			historico = (Historico) query.getResultList().get(0);
		}

		return historico;
	}

	@Override
	public Historico getExtubacaoRecente(Paciente paciente) {
		Historico historico = null;
		Query query = manager
				.createQuery(
						"From Historico h "
								+ "where h.faixa.id = 5 and h.paciente.id = ?1 "
								+ "and h.dataHistorico >= (select max(hh.dataHistorico) from Historico hh where hh.faixa.id = 1 or hh.faixa.id = 6 and hh.paciente.id = ?1) "
								+ "order by h.dataHistorico desc")
				.setParameter(1, paciente.getId());

		try {
			historico = (Historico) query.getSingleResult();
		} catch (NoResultException ex) {

		} catch (NonUniqueResultException ex) {
			historico = (Historico) query.getResultList().get(0);
		}

		return historico;
	}

	@Override
	public Historico getDesmameRecente(Paciente paciente) {
		Historico historico = null;
		Query query = manager
				.createQuery(
						"From Historico h "
								+ "where h.faixa.id = 4 and h.paciente.id = ?1 "
								+ "and h.dataHistorico >= (select max(hh.dataHistorico) from Historico hh where (hh.faixa.id = 1 or hh.faixa.id = 6) and hh.paciente.id = ?1) "
								+ "order by h.dataHistorico desc")
				.setParameter(1, paciente.getId());

		try {
			historico = (Historico) query.getSingleResult();
		} catch (NoResultException ex) {

		} catch (NonUniqueResultException ex) {
			historico = (Historico) query.getResultList().get(0);
		}

		return historico;
	}

	@Override
	public Historico getReintubacaoRecente(Paciente paciente) {
		Historico historico = null;
		Query query = manager
				.createQuery(
						"From Historico h "
								+ "where h.faixa.id = 6 and h.paciente.id = ?1 "
								+ "and h.dataHistorico >= (select max(hh.dataHistorico) from Historico hh where hh.faixa.id = 1 and hh.paciente.id = ?1) "
								+ "order by h.dataHistorico desc")
				.setParameter(1, paciente.getId());

		try {
			historico = (Historico) query.getSingleResult();
		} catch (NoResultException ex) {

		} catch (NonUniqueResultException ex) {
			historico = (Historico) query.getResultList().get(0);
		}

		return historico;
	}

	@Override
	public Historico getMorreu(Paciente paciente) {
		Historico historico = null;
		Query query = manager.createQuery(
				"From Historico h "
						+ "where h.faixa.id = 7 and h.paciente.id = ?1 ")
				.setParameter(1, paciente.getId());

		try {
			historico = (Historico) query.getSingleResult();
		} catch (NoResultException ex) {

		} catch (NonUniqueResultException ex) {
			historico = (Historico) query.getResultList().get(0);
		}

		return historico;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Historico> getPacientesVM() {
		List<Historico> historico = null;
		String sql = "select h.* from faddvm.Historico h "
				+ "where h.dataHistorico in (select max(hh.dataHistorico) from faddvm.Historico hh "
				+ "where (hh.faixa_id = 3 or hh.faixa_id = 6 or hh.faixa_id = 4) "
				+ "and hh.dataHistorico > COALESCE ((select max(hhh.dataHistorico) from faddvm.Historico hhh where (hhh.faixa_id = 5 or hhh.faixa_id = 2) and hhh.paciente_id = hh.paciente_id), 0) "
				+ "and hh.paciente_id not in (select h4.paciente_id from faddvm.Historico h4 where h4.faixa_id = 7) "
				+ "group by hh.paciente_id )";

		historico = manager.createNativeQuery(sql, Historico.class)
				.getResultList();
		if (historico == null) {
			historico = new ArrayList<Historico>();
		}
		return historico;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Historico> getPacientesDesmame() {
		List<Historico> historico = null;
		String sql = "select h.* from faddvm.Historico h "
				+ "where h.dataHistorico in (select max(hh.dataHistorico) from faddvm.Historico hh "
				+ "where hh.faixa_id = 4 "
				+ "and hh.dataHistorico > COALESCE ((select max(hhh.dataHistorico) from faddvm.Historico hhh where (hhh.faixa_id = 5 or hhh.faixa_id = 2) and hhh.paciente_id = hh.paciente_id), 0) "
				+ "and hh.paciente_id not in (select h4.paciente_id from faddvm.Historico h4 where h4.faixa_id = 7) "
				+ "group by hh.paciente_id )";

		historico = manager.createNativeQuery(sql, Historico.class)
				.getResultList();
		if (historico == null) {
			historico = new ArrayList<Historico>();
		}
		return historico;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Historico> getPacientesReintubados() {
		List<Historico> historico = null;
		String sql = "select h.* from faddvm.Historico h "
				+ "where h.dataHistorico in (select max(hh.dataHistorico) from faddvm.Historico hh "
				+ "where hh.faixa_id = 6 "
				+ "and hh.dataHistorico > COALESCE ((select max(hhh.dataHistorico) from faddvm.Historico hhh where (hhh.faixa_id = 5 or hhh.faixa_id = 2) and hhh.paciente_id = hh.paciente_id), 0) "
				+ "and hh.paciente_id not in (select h4.paciente_id from faddvm.Historico h4 where h4.faixa_id = 7) "
				+ "group by hh.paciente_id )";

		historico = manager.createNativeQuery(sql, Historico.class)
				.getResultList();
		if (historico == null) {
			historico = new ArrayList<Historico>();
		}
		return historico;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Historico> getPacientesUTI() {
		List<Historico> historico = null;
		String sql = "select h.* from faddvm.Historico h "
				+ "where h.dataHistorico in (select max(hh.dataHistorico) from faddvm.Historico hh "
				+ "where hh.dataHistorico > COALESCE ((select max(hhh.dataHistorico) from faddvm.Historico hhh where hhh.faixa_id = 2 and hhh.paciente_id = hh.paciente_id), 0) "
				+ "and hh.paciente_id not in (select h4.paciente_id from faddvm.Historico h4 where h4.faixa_id = 7) "
				+ "group by hh.paciente_id )";

		historico = manager.createNativeQuery(sql, Historico.class)
				.getResultList();
		if (historico == null) {
			historico = new ArrayList<Historico>();
		}
		return historico;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Historico> getPacientesExtubados() {
		List<Historico> historico = null;
		String sql = "select h.* from faddvm.Historico h "
				+ "where h.dataHistorico in (select max(hh.dataHistorico) from faddvm.Historico hh "
				+ "where hh.faixa_id = 5 "
				+ "and hh.dataHistorico > COALESCE ((select max(hhh.dataHistorico) from faddvm.Historico hhh where hhh.faixa_id = 2 and hhh.paciente_id = hh.paciente_id), 0) "
				+ "and hh.paciente_id not in (select h4.paciente_id from faddvm.Historico h4 where h4.faixa_id = 7) "
				+ "group by hh.paciente_id )";

		historico = manager.createNativeQuery(sql, Historico.class)
				.getResultList();
		if (historico == null) {
			historico = new ArrayList<Historico>();
		}
		return historico;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Historico> getUltimosAtendimentos() {
		List<Historico> historico = null;
		String sql = "select h.* from faddvm.Historico h order by h.dataHistorico DESC ";

		historico = manager.createNativeQuery(sql, Historico.class)
				.getResultList();
		if (historico == null) {
			historico = new ArrayList<Historico>();
		}
		return historico;
	}

	@Override
	public Historico getSaidaUTIRecente(Paciente paciente) {
		Historico historico = null;

		Query query = manager
				.createQuery(
						"From Historico h "
								+ "where h.faixa.id = 2 and h.paciente.id = ?1 "
								+ "and h.dataHistorico = (select max(hh.dataHistorico) from Historico hh where hh.paciente.id = ?1 and hh.faixa.id = 2)")
				.setParameter(1, paciente.getId());

		try {
			historico = (Historico) query.getSingleResult();
		} catch (NoResultException ex) {

		}

		return historico;
	}

	@Override
	public Historico getUltimoAtendimento(Paciente paciente) {
		Historico historico = null;

		Query query = manager
				.createQuery(
						"From Historico h where h.id = "
								+ "(select max(h1.id) from Historico h1 where h1.paciente.id = ?1)")
				.setParameter(1, paciente.getId());

		try {
			historico = (Historico) query.getSingleResult();
		} catch (NoResultException ex) {

		}

		return historico;
	}

	@Override
	public Historico getAtendimento(Long atendimentoId) {
		Historico historico = null;

		Query query = manager.createQuery("From Historico h where h.id = ?1")
				.setParameter(1, atendimentoId);

		try {
			historico = (Historico) query.getSingleResult();
		} catch (NoResultException ex) {

		}

		return historico;
	}

	@Override
	public void removerAtendimento(Historico historico) {

		Query query = manager.createQuery("delete Historico where id = ?1")
				.setParameter(1, historico.getId());

		query.executeUpdate();

	}

}
