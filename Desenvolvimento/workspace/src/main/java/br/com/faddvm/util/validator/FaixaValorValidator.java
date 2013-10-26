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
			e.reject(null, "Peso Ž obrigat—rio");
			return true;
		}

		if (f.getValorMin() == null) {
			e.reject(null, "Valor m’nimo Ž obrigat—rio.");
			return true;
		}

		if (f.getValorMax() == null) {
			e.reject(null, "Valor m‡ximo Ž obrigat—rio.");
			return true;
		}

		if (f.getValorMin() > f.getValorMax()) {
			e.reject(null,
					"Valor m’nimo deve ser menor ou igual a valor m‡ximo.");
			return true;
		}

		if (f.getValorMin() < 0 || f.getValorMax() < 0) {
			e.reject(null, "Valor m’nimo deve ser menor ou igual a 0");
			return true;
		}
		if (f.getValorMax() < 0) {
			e.reject(null, "Valor m‡ximo deve ser menor ou igual a 0");
			return true;
		}
		if (f.getPeso() < 0) {
			e.reject(null, "Peso deve ser menor ou igual a 0");
			return true;
		}

		if (f.getDescricao() == null) {
			e.reject(null, "Descri‹o Ž obrigat—ria.");
			return true;
		}
		f.setDescricao(f.getDescricao().trim());
		if (f.getDescricao().length() < 3 || f.getDescricao().length() > 250) {
			e.reject(null, "Descri‹o deve ter 3 caracteres ou mais");
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
						"Faixa Inv‡lida, valor m’nimo pode iniciar em "
								+ (vValorMax + 1));
				return true;
			}

			if (faixa.getValorMin() < vValorMin
					&& faixa.getValorMax() != (vValorMin - 1)) {
				errors.reject(null,
						"Faixa Inv‡lida, valor M‡ximo pode terminar em "
								+ (vValorMin - 1));
				return true;
			}

			if (faixa.getValorMax() == vValorMax
					|| faixa.getValorMin() == vValorMin
					|| (faixa.getValorMin() >= vValorMin && faixa.getValorMax() <= vValorMax)) {
				errors.reject(null, "Voc n‹o pode criar essa Faixa");
				return true;
			}

		}
		return false;
	}
}
