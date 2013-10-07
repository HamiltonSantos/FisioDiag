package br.com.faddvm.dao;

import java.util.List;

import br.com.faddvm.model.Fisioterapeuta;

public interface FisioterapeutaDao {

	public Fisioterapeuta salvar(Fisioterapeuta fisioterapeuta);
	
	public List<Fisioterapeuta> lista();
	
	public Fisioterapeuta validaLogin(Fisioterapeuta fisioterapeuta);
	
	public Fisioterapeuta get(Long id);
}
