package br.com.faddvm.util.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.faddvm.model.Variavel;

public class VariavelValidator implements Validator {

	@Override
	public boolean supports(Class<?> classe) {
		return Variavel.class.equals(classe);
	}

	@Override
	public void validate(Object obj, Errors errors) {

		Variavel variavel = (Variavel) obj;

		variavel.setDescricao(variavel.getDescricao().trim());

		if (variavel.getDescricao().length() < 3
				|| variavel.getDescricao().length() > 250) {
			errors.reject(null, "Descrição deve ter no mínimo 3 caracteres");
		}

		if (variavel.getStatus() == null || variavel.getStatus() == 0) {
			errors.reject(null, "Status não pode ser vazio");
		}

		if (variavel.getTipo() == null) {
			errors.reject(null, "Tipo é obrigatório");
		}

	}

}
