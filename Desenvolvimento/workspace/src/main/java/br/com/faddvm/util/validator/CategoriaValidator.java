package br.com.faddvm.util.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
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
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descricao", null, "Descricao nao pode ser Vazia");
		
		if(cat.getStatus() == 0){
			errors.reject(null, "Status nao pode ser Vazio");
		}
	}

}
