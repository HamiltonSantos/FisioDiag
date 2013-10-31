package br.com.faddvm.util.validator;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.faddvm.dao.VariavelDao;
import br.com.faddvm.model.Variavel;

public class VariavelValidator implements Validator {

	VariavelDao dao;

	@Override
	public boolean supports(Class<?> classe) {
		return Variavel.class.equals(classe);
	}

	@Override
	public void validate(Object obj, Errors errors) {

		Variavel variavel = (Variavel) obj;

		variavel.setDescricao(variavel.getDescricao().trim());

		if (variavel.getDescricao().length() < 2
				|| variavel.getDescricao().length() > 30) {
			errors.reject(null, "Descrição deve ter no mínimo 3 caracteres");
		}

		if (variavel.getStatus() == null || variavel.getStatus() == 0) {
			errors.reject(null, "Status não pode ser vazio");
		}

		if (variavel.getTipo() == null) {
			errors.reject(null, "Tipo é obrigatório");
		}

		List<Variavel> variaveisByName = dao
				.getVariaveisCategoriaByName(variavel);

		if (variaveisByName != null && variaveisByName.size() > 0) {
			errors.reject(null,
					"Já existe uma Váriavel com esse nome nessa Categoria.");
		}

	}

	public VariavelValidator(VariavelDao dao) {
		super();
		this.dao = dao;
	}

}
