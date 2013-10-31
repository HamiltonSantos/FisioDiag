package br.com.faddvm.dao;

import java.util.List;

import br.com.faddvm.model.FaixaValor;
import br.com.faddvm.model.Historico;

public interface FaixaValorDao {

	FaixaValor salvar(FaixaValor faixaValor);

	List<FaixaValor> listOcorrencias();

	List<FaixaValor> listaIntercorrencias();

	List<FaixaValor> listaIndices();

	void remover(FaixaValor faixaValor);

	FaixaValor get(Long id);

	FaixaValor getByDescricaoAndVariavel(String descricao, Long idVariavel);

	FaixaValor getByValor(Long valor, Long variavelId);

	List<Historico> getHistoricoByFaixa(FaixaValor faixaValor);

	List<FaixaValor> getFaixasVariavelByName(FaixaValor faixa);
}
