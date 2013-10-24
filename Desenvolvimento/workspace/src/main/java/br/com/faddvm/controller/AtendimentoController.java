package br.com.faddvm.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.faddvm.dao.CategoriaDao;
import br.com.faddvm.dao.FaixaValorDao;
import br.com.faddvm.dao.PacienteDao;
import br.com.faddvm.dao.VariavelDao;
import br.com.faddvm.model.Fisioterapeuta;
import br.com.faddvm.model.Historico;
import br.com.faddvm.model.Paciente;
import br.com.faddvm.model.ValorAtendimento;
import br.com.faddvm.model.Variavel;

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

	@RequestMapping(method = RequestMethod.GET)
	public String home(@PathVariable Long pacienteId, Model model) {

		Paciente paciente = pacienteDao.get(pacienteId);
		model.addAttribute("paciente", paciente);

		model.addAttribute("categorias", categoriaDao.lista());

		model.addAttribute("ocorrencias", faixaValorDao.listOcorrencias());

		model.addAttribute("intercorrencias",
				faixaValorDao.listaIntercorrencias());

		return "/atendimento/home";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@PathVariable Long pacienteId,
			ValorAtendimento valorAtendimento, HttpSession session,
			RedirectAttributes rAttributes) {
		System.out.println(valorAtendimento.getValor() + "Valor - ID"
				+ valorAtendimento.getVariavelId());

		Paciente paciente = pacienteDao.get(pacienteId);

		Historico historico = new Historico();
		
		historico.setDataHistorico(new Date());
		historico.setFisioterapeuta((Fisioterapeuta) session
				.getAttribute("fisioterapeutaLogado"));
		historico.setPaciente(paciente);
		Variavel variavel = variavelDao.get(valorAtendimento.getVariavelId());
		historico.setVariavel(variavel);
		historico.setValor(valorAtendimento.getValor());

		paciente.getHistorico().add(historico);

		pacienteDao.salvar(paciente);
		rAttributes.addFlashAttribute("msgSucesso",
				"Atendimento cadastrado com Sucesso");
		return "redirect:/atendimento/" + pacienteId;
	}

	@RequestMapping(value = "/detalhe")
	public String detalhe(@PathVariable Long pacienteId, Model model) {

		model.addAttribute("paciente", pacienteDao.get(pacienteId));

		return "/atendimento/detalhe";
	}
}
