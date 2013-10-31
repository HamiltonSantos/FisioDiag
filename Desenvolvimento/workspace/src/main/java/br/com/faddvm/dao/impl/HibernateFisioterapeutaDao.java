package br.com.faddvm.dao.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.faddvm.dao.FisioterapeutaDao;
import br.com.faddvm.model.Fisioterapeuta;
import br.com.faddvm.model.Historico;

@Repository
public class HibernateFisioterapeutaDao implements FisioterapeutaDao {

	@PersistenceContext
	EntityManager manager;

	@Override
	public Fisioterapeuta salvar(Fisioterapeuta fisioterapeuta) {
		String senhaCrip = senhaCriptografada(fisioterapeuta.getSenha());
		fisioterapeuta.setSenha(senhaCrip);
		if (fisioterapeuta.getLogin() == null) {
			manager.persist(fisioterapeuta);
		} else {
			manager.merge(fisioterapeuta);
		}

		return fisioterapeuta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fisioterapeuta> lista() {
		return (List<Fisioterapeuta>) manager
				.createQuery("From Fisioterapeuta").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Fisioterapeuta validaLogin(Fisioterapeuta fisioterapeuta) {
		String senhaCrip = senhaCriptografada(fisioterapeuta.getSenha());

		List<Fisioterapeuta> lista = (List<Fisioterapeuta>) manager
				.createQuery(
						"from Fisioterapeuta as "
								+ "f where f.login = ?1 and f.senha = ?2")
				.setParameter(1, fisioterapeuta.getLogin())
				.setParameter(2, senhaCrip).getResultList();

		if (lista.size() > 0) {
			return lista.get(0);
		}
		return null;
	}

	@Override
	public Fisioterapeuta get(Long id) {
		return manager.find(Fisioterapeuta.class, id);
	}

	private String senhaCriptografada(String senhaDescript) {
		MessageDigest algorithm;
		try {
			algorithm = MessageDigest.getInstance("MD5");
			byte messageDigest[] = algorithm.digest(senhaDescript
					.getBytes("UTF-8"));

			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Fisioterapeuta getByLogin(String login) {
		Fisioterapeuta fisioterapeuta = null;
		Query query = manager.createQuery(
				"From Fisioterapeuta f where f.login = ?1").setParameter(1,
				login);

		try {
			fisioterapeuta = (Fisioterapeuta) query.getSingleResult();
		} catch (NoResultException ex) {

		}
		return fisioterapeuta;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Historico> getAtendimentosFisioterapeuta(
			Fisioterapeuta fisioterapeuta) {
		List<Historico> historico = null;
		String sql = "From Historico h where h.fisioterapeuta.id = ?1 and h.faixa.variavel.id != 1 and h.faixa.variavel.id != 2 order by h.dataHistorico DESC ";

		historico = manager.createQuery(sql)
				.setParameter(1, fisioterapeuta.getId()).getResultList();
		if (historico == null) {
			historico = new ArrayList<Historico>();
		}
		return historico;
	}

	@Override
	public Long getNumeroAtendimentos(Fisioterapeuta fisioterapeuta) {
		Long result = null;
		Query query = manager
				.createQuery(
						"select count(h.id) From Historico h where h.fisioterapeuta.id = ?1 and h.faixa.variavel.id != 1 and h.faixa.variavel.id != 2")
				.setParameter(1, fisioterapeuta.getId());

		try {
			result = (Long) query.getSingleResult();
		} catch (NoResultException ex) {

		}
		return result;
	}

	@Override
	public Long getNumeroDesmames(Fisioterapeuta fisioterapeuta) {
		Long result = null;
		Query query = manager
				.createQuery(
						"select count(h.id) From Historico h where h.fisioterapeuta.id = ?1 and h.faixa.id = 4")
				.setParameter(1, fisioterapeuta.getId());

		try {
			result = (Long) query.getSingleResult();
		} catch (NoResultException ex) {

		}
		return result;
	}

	@Override
	public Long getNumeroExtubacoes(Fisioterapeuta fisioterapeuta) {
		Long result = null;
		Query query = manager
				.createQuery(
						"select count(h.id) From Historico h where h.fisioterapeuta.id = ?1 and h.faixa.id = 5")
				.setParameter(1, fisioterapeuta.getId());

		try {
			result = (Long) query.getSingleResult();
		} catch (NoResultException ex) {

		}
		return result;
	}

	@Override
	public Long getNumeroReintubacoes(Fisioterapeuta fisioterapeuta) {
		Long result = null;
		Query query = manager
				.createQuery(
						"select count(h.id) From Historico h where h.fisioterapeuta.id = ?1 and h.faixa.id = 6")
				.setParameter(1, fisioterapeuta.getId());

		try {
			result = (Long) query.getSingleResult();
		} catch (NoResultException ex) {

		}
		return result;
	}

	@Override
	public Long getNumeroIntubacoes(Fisioterapeuta fisioterapeuta) {
		Long result = null;
		Query query = manager
				.createQuery(
						"select count(h.id) From Historico h where h.fisioterapeuta.id = ?1 and h.faixa.id = 3")
				.setParameter(1, fisioterapeuta.getId());

		try {
			result = (Long) query.getSingleResult();
		} catch (NoResultException ex) {

		}
		return result;
	}
}
