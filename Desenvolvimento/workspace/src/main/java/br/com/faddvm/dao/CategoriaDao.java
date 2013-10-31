package br.com.faddvm.dao;

import java.util.List;

import br.com.faddvm.model.Categoria;
import br.com.faddvm.model.Variavel;

public interface CategoriaDao {

	Categoria salvar(Categoria categoria);

	Categoria get(Long id);

	List<Categoria> lista();

	void remover(Categoria categoria);

	Categoria getByDescricao(String descricao);
	
	List<Variavel> getVariaveisCategoria(Categoria categoria);
}
