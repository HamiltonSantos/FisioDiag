package br.com.faddvm.util.validator;

import org.springframework.validation.Errors;
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

		if (validaFaixa(faixa, errors)) {
			return;
		}

		if (validaRange(faixa, errors)) {
			return;
		}

	}

	private boolean validaFaixa(FaixaValor f, Errors e) {

		if (f.getPeso() == null) {
			e.reject(null, "Peso e obrigatorio");
			return true;
		}

		if (f.getValorMin() == null) {
			e.reject(null, "Valor minimo e obrigatorio.");
			return true;
		}

		if (f.getValorMax() == null) {
			e.reject(null, "Valor maximo e obrigatorio.");
			return true;
		}

		if (f.getValorMin() > f.getValorMax()) {
			e.reject(null,
					"Valor minimo tem que ser menor ou igual a valor maximo.");
			return true;
		}

		if (f.getValorMin() < 0 || f.getValorMax() < 0) {
			e.reject(null, "Valor minimo tem que ser menor ou igual a 0");
			return true;
		}
		if (f.getValorMax() < 0) {
			e.reject(null, "Valor maximo tem que ser menor ou igual a 0");
			return true;
		}
		if (f.getPeso() < 0) {
			e.reject(null, "Peso tem que ser menor ou igual a 0");
			return true;
		}

		if (f.getDescricao() == null) {
			e.reject(null, "Descricao e obrigatoria.");
			return true;
		}
		f.setDescricao(f.getDescricao().trim());
		if (f.getDescricao().length() < 3 || f.getDescricao().length() > 250) {
			e.reject(null, "Descricao tem que ter mais que 3 caracteres");
			return true;
		}

		return false;
	}

	private boolean validaRange(FaixaValor faixa, Errors errors) {
		Integer vValorMin = faixa.getVariavel().getValorMin();
		Integer vValorMax = faixa.getVariavel().getValorMax();

		boolean vIniciada = vValorMax != null && vValorMin != null;
		char vTipo = faixa.getVariavel().getTipo();
		if (vIniciada && vTipo == 'R') {
			if (faixa.getValorMax() > vValorMax
					&& faixa.getValorMin() != (vValorMax + 1)) {
				errors.reject(null,
						"Faixa Invalida, valor minimo pode iniciar em "
								+ (vValorMax + 1));
				return true;
			}

			if (faixa.getValorMin() < vValorMin
					&& faixa.getValorMax() != (vValorMin - 1)) {
				errors.reject(null,
						"Faixa Invalida, valor Maximo pode terminar em "
								+ (vValorMin - 1));
				return true;
			}

			if (faixa.getValorMax() == vValorMax
					|| faixa.getValorMin() == vValorMin
					|| (faixa.getValorMin() >= vValorMin && faixa.getValorMax() <= vValorMax)) {
				errors.reject(null, "Voce nao pode criar essa Faixa");
				return true;
			}

		}
		return false;
	}
}
