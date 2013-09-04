package br.com.faddvm.dao;

import java.util.List;

import br.com.faddvm.model.Categoria;

public interface CategoriaDao {

	public Categoria salva(Categoria categoria);

	public Categoria get(Long id);
	
	public List<Categoria> lista();
}
