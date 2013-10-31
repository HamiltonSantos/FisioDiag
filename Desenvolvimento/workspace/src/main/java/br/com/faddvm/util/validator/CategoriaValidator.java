package br.com.faddvm.util.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.faddvm.dao.CategoriaDao;
import br.com.faddvm.model.Categoria;

public class CategoriaValidator implements Validator {

	CategoriaDao dao;

	@Override
	public boolean supports(Class<?> classe) {
		return Categoria.class.equals(classe);
	}

	@Override
	public void validate(Object categoria, Errors errors) {
		Categoria cat = (Categoria) categoria;

		cat.setDescricao(cat.getDescricao().trim());

		if (cat.getDescricao().length() < 2
				|| cat.getDescricao().length() > 30) {
			errors.reject(null, "Descrição deve ter no mínimo 3 caracteres");
		}

		if (cat.getStatus() == 0) {
			errors.reject(null, "Status não pode ser Vazio");
		}

		if (dao.getByDescricao(cat.getDescricao()) != null
				&& cat.getId() == null) {
			errors.reject(null, "Categoria com essa descrição Cadastrada");
		}

	}

	public CategoriaValidator(CategoriaDao dao) {
		super();
		this.dao = dao;
	}

}
