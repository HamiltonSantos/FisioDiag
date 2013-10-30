package br.com.faddvm.util.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.faddvm.dao.PacienteDao;
import br.com.faddvm.model.FaixaValor;
import br.com.faddvm.model.Historico;
import br.com.faddvm.model.Paciente;

public class HistoricoValidator implements Validator {

	PacienteDao pacienteDao;

	public HistoricoValidator(PacienteDao pacienteDao) {
		super();
		this.pacienteDao = pacienteDao;
	}

	@Override
	public boolean supports(Class<?> classe) {
		return Historico.class.equals(classe);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Historico historico = (Historico) obj;

		if (historico.getValor() == null) {
			errors.reject(null,
					"Não é permitido inserir histórico com valor vazio.");
			return;
		}

		if (historico.getFaixa() == null) {
			errors.reject(null, "Erro ao inserir atendimento.");
			return;
		}

		if (pacienteDao.getMorreu(historico.getPaciente()) != null) {
			errors.reject(null,
					"Impossivel inserir atendimento, esse paciente faleceu.");
			return;
		}

		Historico local = pacienteDao.getEntradaSaidaRecente(historico
				.getPaciente());
		if ((local == null || local.getFaixa().getId() == 2 || pacienteDao
				.getEntradaUTIRecente(historico.getPaciente()) == null)
				&& historico.getFaixa().getId() != 1) {
			errors.reject(null,
					"Paciente precisa estar na UTI para cadastrar Atendimento.");
			return;
		}

		if (historico.getFaixa().getVariavel().getId() == 1) {
			validaOcorrencias(historico, errors);
		}
	}

	// Valida Ocorrencias
	private void validaOcorrencias(Historico h, Errors errors) {

		FaixaValor f = h.getFaixa();
		Paciente p = h.getPaciente();

		Historico entradaRecente = pacienteDao.getEntradaSaidaRecente(p);
		Historico entradaVMRecente = pacienteDao.getEntradaVMRecente(p);
		Historico extubacaoRecente = pacienteDao.getExtubacaoRecente(p);
		Historico desmameRecente = pacienteDao.getDesmameRecente(p);
		// Historico reintubacaoRecente = pacienteDao.getReintubacaoRecente(p);

		// verifica se esta querendo entrar na uti
		if (entradaRecente != null && f.getId() == 1
				&& entradaRecente.getFaixa().getId() == 1) {
			errors.reject(null, "Paciente já esta na UTI.");
			return;
		}

		// verifica se esta querendo entrar em vm
		if (entradaVMRecente != null && f.getId() == 3
				&& extubacaoRecente == null) {
			errors.reject(null, "Paciente já esta em VM.");
			return;
		}

		// verifica se ja foi extubado para poder sair UTI
		if (extubacaoRecente == null && f.getId() == 2
				&& entradaVMRecente != null) {
			errors.reject(null,
					"Paciente precisa ser extubado para poder sair da UTI.");
			return;
		}

		// verefica se esta em VM para Inicar Desmame
		if ((entradaVMRecente == null && f.getId() == 4)
				|| (entradaVMRecente != null && extubacaoRecente != null && f
						.getId() == 4)) {
			errors.reject(null,
					"Paciente precisa estar em VM para Iniciar Desmame.");
			return;
		}

		// verifica se ja foi extubado e quer inicar V.M de novo
		if (extubacaoRecente != null && entradaVMRecente != null
				&& f.getId() == 3) {
			errors.reject(null,
					"Já foi iniciado VM do paciente, favor selecionar Reintubação.");
			return;
		}

		// verefica se ja inicou vm para Fazer extubacao
		if (entradaVMRecente == null && f.getId() == 5
				&& extubacaoRecente == null) {
			errors.reject(null,
					"Paciente precisa estar em VM para fazer extubação.");
			return;
		}

		// verifia se ja foi e extubado e quer extubar de novo
		if (extubacaoRecente != null && f.getId() == 5) {
			errors.reject(null, "Paciente já foi extubado.");
			return;
		}

		// verifica se ja esta em desmame
		if (desmameRecente != null && f.getId() == 4
				&& extubacaoRecente == null) {
			errors.reject(null, "Paciente já esta em Desmame.");
			return;
		}

		// verifica se ja foi desmamado
		if (desmameRecente != null && extubacaoRecente != null
				&& f.getId() == 4) {
			errors.reject(null, "Paciente já foi em Desmamado.");
			return;
		}

		// verifica se ja foi extubado para poder ser reintubado
		if (extubacaoRecente == null && f.getId() == 6) {
			errors.reject(null,
					"Paciente não foi extubado para poder ser Reintubado.");
			return;
		}

	}

}
