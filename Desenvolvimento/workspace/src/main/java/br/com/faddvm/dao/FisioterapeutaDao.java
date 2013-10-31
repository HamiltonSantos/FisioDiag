package br.com.faddvm.dao;

import java.util.List;

import br.com.faddvm.model.Fisioterapeuta;
import br.com.faddvm.model.Historico;

public interface FisioterapeutaDao {

	Fisioterapeuta salvar(Fisioterapeuta fisioterapeuta);

	List<Fisioterapeuta> lista();

	Fisioterapeuta validaLogin(Fisioterapeuta fisioterapeuta);

	Fisioterapeuta get(Long id);

	Fisioterapeuta getByLogin(String login);

	List<Historico> getAtendimentosFisioterapeuta(Fisioterapeuta fisioterapeuta);

	Long getNumeroAtendimentos(Fisioterapeuta fisioterapeuta);

	Long getNumeroDesmames(Fisioterapeuta fisioterapeuta);

	Long getNumeroExtubacoes(Fisioterapeuta fisioterapeuta);
	
	Long getNumeroReintubacoes(Fisioterapeuta fisioterapeuta);
	
	Long getNumeroIntubacoes(Fisioterapeuta fisioterapeuta);
}
