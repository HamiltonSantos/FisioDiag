package br.com.faddvm.util.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.faddvm.model.Categoria;

public class CategoriaValidator implements Validator {

	@Override
	public boolean supports(Class<?> classe) {
		return Categoria.class.equals(classe);
	}

	@Override
	public void validate(Object categoria, Errors errors) {
		Categoria cat = (Categoria) categoria;

		cat.setDescricao(cat.getDescricao().trim());

		if (cat.getDescricao().length() < 3
				|| cat.getDescricao().length() > 250) {
			errors.reject(null, "Descrição deve ter no mínimo 3 caracteres");
		}

		if (cat.getStatus() == 0) {
			errors.reject(null, "Status não pode ser Vazio");
		}
	}

}
