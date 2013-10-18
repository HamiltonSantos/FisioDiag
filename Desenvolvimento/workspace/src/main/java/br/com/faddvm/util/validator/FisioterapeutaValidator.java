package br.com.faddvm.util.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.faddvm.model.Fisioterapeuta;

public class FisioterapeutaValidator implements Validator {

	@Override
	public boolean supports(Class<?> classe) {
		return Fisioterapeuta.class.equals(classe);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
	}

}
