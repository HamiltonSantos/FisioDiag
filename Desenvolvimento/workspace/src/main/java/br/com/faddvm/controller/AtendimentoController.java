package br.com.faddvm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.faddvm.dao.CategoriaDao;
import br.com.faddvm.dao.FaixaValorDao;
import br.com.faddvm.dao.PacienteDao;
import br.com.faddvm.dao.VariavelDao;
import br.com.faddvm.model.FaixaValor;
import br.com.faddvm.model.Fisioterapeuta;
import br.com.faddvm.model.Historico;
import br.com.faddvm.model.Paciente;
import br.com.faddvm.model.ValorAtendimento;
import br.com.faddvm.util.validator.HistoricoValidator;

@Controller
@RequestMapping("/atendimento/{pacienteId}")
@Transactional
public class AtendimentoController {

	@Autowired
	@Qualifier("hibernatePacienteDao")
	PacienteDao pacienteDao;

	@Autowired
	@Qualifier("hibernateCategoriaDao")
	CategoriaDao categoriaDao;

	@Autowired
	@Qualifier("hibernateVariavelDao")
	VariavelDao variavelDao;

	@Autowired
	@Qualifier("hibernateFaixaValorDao")
	FaixaValorDao faixaValorDao;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@RequestMapping(method = RequestMethod.GET)
	public String home(@PathVariable Long pacienteId, Model model,
			RedirectAttributes rAttributes) {

		Paciente paciente = pacienteDao.get(pacienteId);

		if (paciente == null) {
			rAttributes.addFlashAttribute("msgErro", "Paciente não encontrado");
			return "redirect:/paciente";
		}

		model.addAttribute("paciente", paciente);
		model.addAttribute("indicacao",
				pacienteDao.getIndicacaoPaciente(paciente));

		model.addAttribute("categorias", categoriaDao.lista());

		model.addAttribute("ocorrencias", faixaValorDao.listOcorrencias());

		model.addAttribute("intercorrencias",
				faixaValorDao.listaIntercorrencias());

		model.addAttribute("ultimoAtendimento",
				pacienteDao.getUltimoAtendimento(paciente));

		return "/atendimento/home";
	}

	@RequestMapping(value = "/{atendimentoId}/remover ", method = RequestMethod.GET)
	public String removerAtendimento(@PathVariable Long pacienteId,
			@PathVariable Long atendimentoId, RedirectAttributes rAttributes) {

		Paciente paciente = pacienteDao.get(pacienteId);

		if (paciente == null) {
			rAttributes.addFlashAttribute("msgErro", "Paciente não encontrado");
			return "redirect:/paciente";
		}

		Historico atendimento = pacienteDao.getAtendimento(atendimentoId);

		if (atendimento == null) {
			rAttributes.addFlashAttribute("msgErro",
					"Atendimento não encontrado");
			return "redirect:/atendimento/" + pacienteId;
		}
		Historico ultimoAtendimento = pacienteDao
				.getUltimoAtendimento(paciente);

		if (ultimoAtendimento == null) {
			rAttributes.addFlashAttribute("msgErro",
					"Não é possivel realizar essa operação");
			return "redirect:/atendimento/" + pacienteId;
		}

		if (ultimoAtendimento.getId() != atendimento.getId()) {
			rAttributes.addFlashAttribute("msgErro",
					"Só é possivel desfazer o ultimo Atendimento");
			return "redirect:/atendimento/" + pacienteId;
		}

		pacienteDao.removerAtendimento(atendimento);

		rAttributes.addFlashAttribute("msgSucesso", "Atendimento desfeito.");

		return "redirect:/atendimento/" + pacienteId;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@PathVariable Long pacienteId,
			ValorAtendimento valorAtendimento, HttpSession session,
			RedirectAttributes rAttributes, BindingResult errors) {

		Paciente paciente = pacienteDao.get(pacienteId);
		FaixaValor faixa = null;
		if (valorAtendimento.getFaixaId() != null) {
			faixa = faixaValorDao.get(valorAtendimento.getFaixaId());
		} else {
			faixa = faixaValorDao.getByValor(valorAtendimento.getValor(),
					valorAtendimento.getVariavelId());
		}

		Historico historico = new Historico();

		if (valorAtendimento.getDataHistorico() == null) {
			historico.setDataHistorico(new Date());
		} else {
			historico.setDataHistorico(valorAtendimento.getDataHistorico());
		}
		historico.setFisioterapeuta((Fisioterapeuta) session
				.getAttribute("fisioterapeutaLogado"));
		historico.setPaciente(paciente);
		historico.setFaixa(faixa);

		if (faixa != null && faixa.getVariavel().getTipo() == 'O') {
			historico.setValor(new Long(faixa.getPeso()));
		} else {
			historico.setValor(valorAtendimento.getValor());
		}

		ValidationUtils.invokeValidator(new HistoricoValidator(pacienteDao),
				historico, errors);

		if (errors.hasErrors()) {
			rAttributes.addFlashAttribute("msgErro", errors.getGlobalError()
					.getDefaultMessage());
			return "redirect:/atendimento/" + pacienteId;
		}

		paciente.getHistorico().add(historico);
		pacienteDao.salvar(paciente);
		rAttributes.addFlashAttribute("msgSucesso",
				"Atendimento cadastrado com Sucesso");
		return "redirect:/atendimento/" + pacienteId;
	}

}
