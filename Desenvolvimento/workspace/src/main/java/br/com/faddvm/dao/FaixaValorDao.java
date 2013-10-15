package br.com.faddvm.dao;

import java.util.List;

import br.com.faddvm.model.FaixaValor;

public interface FaixaValorDao {

	public FaixaValor salvar(FaixaValor faixaValor);
	
	public List<FaixaValor> listOcorrencias();
	
	public List<FaixaValor> listaIntercorrencias();
	
	public List<FaixaValor> listaIndices();
	
	public int getValorMinIndice();
}
