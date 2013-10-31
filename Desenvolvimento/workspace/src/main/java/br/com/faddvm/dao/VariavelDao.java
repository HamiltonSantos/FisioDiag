package br.com.faddvm.dao;

import java.util.List;

import br.com.faddvm.model.FaixaValor;
import br.com.faddvm.model.Variavel;

public interface VariavelDao {

	Variavel salvar(Variavel variavel);

	Variavel get(Long long1);

	void remove(Variavel variavel);

	List<FaixaValor> getFaixasByVariavel(Variavel variavel);
}
