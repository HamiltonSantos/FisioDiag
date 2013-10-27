package br.com.faddvm.util.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.faddvm.model.Historico;

public class HistoricoValidator implements Validator {

	@Override
	public boolean supports(Class<?> classe) {
		return Historico.class.equals(classe);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Historico historico = (Historico) obj;
		
		if(historico.getValor() == null){
			errors.reject(null, "Não é permitido inserir histórico com valor vazio.");
		}
		
	}

}
