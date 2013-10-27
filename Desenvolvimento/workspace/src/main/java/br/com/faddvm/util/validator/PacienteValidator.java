package br.com.faddvm.util.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.faddvm.dao.PacienteDao;
import br.com.faddvm.model.Paciente;

public class PacienteValidator implements Validator {

	PacienteDao dao;

	@Override
	public boolean supports(Class<?> classe) {
		return Paciente.class.equals(classe);
	}

	public PacienteValidator(PacienteDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public void validate(Object obj, Errors errors) {

		Paciente p = (Paciente) obj;

		p.setNome(p.getNome().trim());
		p.setNumRegistro(p.getNumRegistro().trim());

		if (p.getNome().length() < 3 || p.getNome().length() > 250) {
			errors.reject(null, "Nome deve ter no mínimo 3 caracteres");
		}

		if (p.getNumRegistro().length() < 3
				|| p.getNumRegistro().length() > 250) {
			errors.reject(null,
					"Número de registro deve ter no mínimo 3 caracteres");
		}

		if (p.getId() == null) {
			if (dao.getByCPF(p.getCpf()) != null) {
				errors.reject(null, "Ja existe paciente com esse CPF");
			}

			if (dao.getByNumRegistro(p.getNumRegistro()) != null) {
				errors.reject(null,
						"Ja existe paciente com esse Numero de Registro");
			}
		}

	}

}
