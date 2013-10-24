package br.com.faddvm.dao;

import java.util.List;

import br.com.faddvm.model.Categoria;

public interface CategoriaDao {

	Categoria salvar(Categoria categoria);

	Categoria get(Long id);

	List<Categoria> lista();

	void remover(Categoria categoria);

}
