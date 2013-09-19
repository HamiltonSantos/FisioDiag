package br.com.faddvm.util.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.faddvm.model.Paciente;

public class PacienteValidator implements Validator {

	@Override
	public boolean supports(Class classe) {
		return Paciente.class.equals(classe);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Paciente paciente = (Paciente) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", null,
				"Nome não pode ser Vazio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cpf", null,
				"CPF não pode ser Vazio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numRegistro", null,
				"Numero de Registro não pode ser Vazio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataNascimento", null,
				"Data de Nascimento não pode ser Vazia");
		if(paciente.getSexo() == 0) {
			errors.reject(null,"Sexo não pode ser Vazio");
		}
	}

}
