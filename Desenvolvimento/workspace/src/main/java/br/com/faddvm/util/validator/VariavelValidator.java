package br.com.faddvm.util.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
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

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descricao", null,
				"Descricao nao pode ser vazia");

		if (variavel.getStatus() == null || variavel.getStatus() == 0) {
			errors.reject(null, "Status nao pode ser vazio");
		}
		if (variavel.getTipo() == null || variavel.getTipo() == 0) {
			errors.reject(null, "Tipo nao pode ser vazio");
		}
	}

}
