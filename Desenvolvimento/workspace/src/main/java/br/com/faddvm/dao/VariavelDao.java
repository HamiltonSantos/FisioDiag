package br.com.faddvm.dao;

import br.com.faddvm.model.Variavel;

public interface VariavelDao {

	Variavel salvar(Variavel variavel);

	Variavel get(Long long1);

	void remove(Variavel variavel);
}
