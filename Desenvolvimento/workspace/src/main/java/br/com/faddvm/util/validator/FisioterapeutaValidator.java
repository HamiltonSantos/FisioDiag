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
		Fisioterapeuta fisioterapeuta = (Fisioterapeuta) obj;

		fisioterapeuta.setNome(fisioterapeuta.getNome().trim());
		fisioterapeuta.setLogin(fisioterapeuta.getLogin().trim());

		if (fisioterapeuta.getLogin().length() < 3
				|| fisioterapeuta.getLogin().length() > 250) {
			errors.reject(null, "Login deve ter no m�nimo 3 caracteres");
		}

		if (fisioterapeuta.getNome().length() < 3
				|| fisioterapeuta.getNome().length() > 250) {
			errors.reject(null, "Nome deve ter no m�nimo 3 caracteres");
		}

		if (!fisioterapeuta.getSenha().equals(fisioterapeuta.getContraSenha())) {
			errors.reject(null, "Senha e contra Senha devem ser iguais");
		}
	}

}
