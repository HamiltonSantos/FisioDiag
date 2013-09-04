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

import br.com.faddvm.dao.CategoriaDao;
import br.com.faddvm.dao.PacienteDao;
import br.com.faddvm.dao.VariavelDao;
import br.com.faddvm.model.Fisioterapeuta;
import br.com.faddvm.model.Historico;
import br.com.faddvm.model.Paciente;
import br.com.faddvm.model.ValorAtendimento;

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

	@RequestMapping(method = RequestMethod.GET)
	public String get(@PathVariable Long pacienteId, Model model) {

		model.addAttribute("paciente", pacienteDao.get(pacienteId));

		model.addAttribute("categorias", categoriaDao.lista());

		return "/atendimento/home";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@PathVariable Long pacienteId,
			ValorAtendimento valorAtendimento, HttpSession session) {
		System.out.println(valorAtendimento.getValor() + "Valor - ID"
				+ valorAtendimento.getVariavelId());

		Paciente paciente = pacienteDao.get(pacienteId);

		Historico historico = new Historico();
		// SetarData
		historico.setData(new Date());
		// SetarFisioterapeuta
		historico.setFisioterapeuta((Fisioterapeuta) session
				.getAttribute("fisioterapeutaLogado"));
		// SetarPaciente
		historico.setPaciente(paciente);
		// SetarVariavel
		historico
				.setVariavel(variavelDao.get(valorAtendimento.getVariavelId()));
		// SetarValor
		historico.setValor(valorAtendimento.getValor());

		// Adiciona o historico no paciente
		paciente.getHistorico().add(historico);
		// Atualiza no banco
		pacienteDao.atualiza(paciente);
		// //Atualizar Indicação do Paciente
		return "redirect:/atendimento/" + pacienteId;
	}

}
