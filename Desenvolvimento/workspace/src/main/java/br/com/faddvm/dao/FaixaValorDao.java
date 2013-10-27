package br.com.faddvm.dao;

import java.util.List;

import br.com.faddvm.model.FaixaValor;

public interface FaixaValorDao {

	FaixaValor salvar(FaixaValor faixaValor);

	List<FaixaValor> listOcorrencias();

	List<FaixaValor> listaIntercorrencias();

	List<FaixaValor> listaIndices();

	void remover(FaixaValor faixaValor);

	FaixaValor get(Long id);
	
	FaixaValor getByDescricaoAndVariavel(String descricao, Long idVariavel);
}
