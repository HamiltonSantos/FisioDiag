package br.com.faddvm.util.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.faddvm.model.Paciente;

public class PacienteValidator implements Validator {

	@Override
	public boolean supports(Class<?> classe) {
		return Paciente.class.equals(classe);
	}

	@Override
	public void validate(Object obj, Errors errors) {

		Paciente p = (Paciente) obj;

		p.setNome(p.getNome().trim());
		p.setNumRegistro(p.getNumRegistro().trim());

		if (p.getNome().length() < 3 || p.getNome().length() > 250) {
			errors.reject(null, "Nome deve ter no m’nimo 3 caracteres");
		}

		if (p.getNumRegistro().length() < 3
				|| p.getNumRegistro().length() > 250) {
			errors.reject(null,
					"Nœmero de registro deve ter no m’nimo 3 caracteres");
		}

	}

}
