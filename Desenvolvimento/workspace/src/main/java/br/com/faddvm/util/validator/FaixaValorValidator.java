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
		Integer valorMinVar = faixa.getVariavel().getValorMin();
		Integer valorMaxVar = faixa.getVariavel().getValorMax();
		boolean faixaNotNull = faixa.getValorMin() != null
				&& faixa.getValorMax() != null;
		boolean isOcorrenciaOrIntercorrencia = faixa.getVariavel().getId() == 1
				|| faixa.getVariavel().getId() == 2;
		boolean variavelNotNull = valorMaxVar != null && valorMinVar != null;
		if (variavelNotNull) {
			valorMinVar--;
			valorMaxVar++;
		} else {
			valorMinVar = 0;
			valorMaxVar = 0;
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descricao", null,
				"Descricao nao pode ser Vazio");

		if (faixa.getPeso() == null) {
			errors.reject(null, "Peso nao pode ser Vazio");
		}

		if (faixa.getValorMin() == null) {
			errors.reject(null, "Valor Minimo nao pode ser Vazio");
		}

		if (faixa.getValorMax() == null) {
			errors.reject(null, "Valor Maximo nao pode ser Vazio");
		}
		if (faixa.getValorMin() < 0) {
			errors.reject(null, "Valor Minimo nao pode ser menor que 0");
		}

		if (faixaNotNull && !isOcorrenciaOrIntercorrencia) {
			boolean faixaValida = faixa.getValorMin() <= faixa.getValorMax();
			if (faixaValida) {
				if (variavelNotNull) {
					if (faixa.getValorMin() < valorMaxVar
							&& faixa.getValorMax() != valorMinVar) {
						errors.reject(null,
								"Faixa invalida, valor maximo pode terminar em: "
										+ valorMinVar);
					}

					if (faixa.getValorMax() > valorMinVar
							&& faixa.getValorMin() != valorMaxVar) {
						errors.reject(null,
								"Faixa invalida, valor minimo pode iniciar em: "
										+ valorMaxVar);
					}
				}

			} else {
				errors.reject(null,
						"Valor Minimo tem que ser menor que Valor Maximo");
			}
		}

	}
}
