package br.com.faddvm.util.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.faddvm.model.FaixaValor;

public class FaixaValorValidator implements Validator {

	@Override
	public boolean supports(Class<?> classe) {
		return FaixaValor.class.equals(classe);
	}

	@Override
	public void validate(Object obj, Errors errors) {

		FaixaValor faixa = (FaixaValor) obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descricao", null,
				"Descricao nao pode ser Vazio");

		if(faixa.getPeso() == null){
			errors.reject(null,"Peso nao pode ser Vazio");
		}
		
		if (faixa.getValorMin() != null && faixa.getValorMax() != null) {

			if (faixa.getValorMax() < faixa.getValorMin()) {
				errors.reject(null,
						"Valor Maximo tem que ser maior que Valor Minimo");
			}
		}

	}
}
