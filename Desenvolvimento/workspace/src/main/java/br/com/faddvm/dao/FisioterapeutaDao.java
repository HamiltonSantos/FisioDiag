package br.com.faddvm.dao;

import java.util.List;

import br.com.faddvm.model.Fisioterapeuta;

public interface FisioterapeutaDao {

	Fisioterapeuta salvar(Fisioterapeuta fisioterapeuta);

	List<Fisioterapeuta> lista();

	Fisioterapeuta validaLogin(Fisioterapeuta fisioterapeuta);

	Fisioterapeuta get(Long id);

	Fisioterapeuta getByLogin(String login);
}
