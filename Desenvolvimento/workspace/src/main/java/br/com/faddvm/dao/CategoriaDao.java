package br.com.faddvm.dao;

import br.com.faddvm.model.Categoria;

public interface CategoriaDao {

	public Categoria salva(Categoria categoria);

	public Categoria get(Long long1);
	
}
