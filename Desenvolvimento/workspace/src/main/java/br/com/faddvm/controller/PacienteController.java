package br.com.faddvm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

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

import br.com.faddvm.dao.PacienteDao;
import br.com.faddvm.model.Paciente;
import br.com.faddvm.util.validator.PacienteValidator;

@Transactional
@Controller
@RequestMapping("/paciente")
public class PacienteController {

	@Autowired
	@Qualifier("hibernatePacienteDao")
	PacienteDao dao;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("pacientes", dao.lista());
		return "/paciente/home";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Valid Paciente paciente, BindingResult result,
			RedirectAttributes rAttributes) {

		ValidationUtils.invokeValidator(new PacienteValidator(dao), paciente,
				result);

		if (result.hasErrors()) {
			return "/paciente/form";
		}

		dao.salvar(paciente);
		rAttributes.addFlashAttribute("msgSucesso",
				"Paciente cadastrado com Sucesso");
		return "redirect:/paciente";
	}

	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public String novo(Model model) {
		model.addAttribute("paciente", new Paciente());
		return "/paciente/form";
	}

	@RequestMapping(value = "/{pacienteId}", method = RequestMethod.GET)
	public String mostra(@PathVariable Long pacienteId, Model model,
			RedirectAttributes rAttributes) {

		Paciente paciente = dao.get(pacienteId);
		if (paciente == null) {
			rAttributes.addFlashAttribute("msgErro", "Paciente não encontrado");
			return "redirect:/paciente";
		}
		model.addAttribute("paciente", paciente);
		return "/paciente/mostra";
	}

	@RequestMapping(value = "/{pacienteId}/editar", method = RequestMethod.GET)
	public String editar(@PathVariable Long pacienteId, Model model,
			RedirectAttributes rAttributes) {

		Paciente paciente = dao.get(pacienteId);
		if (paciente == null) {
			rAttributes.addFlashAttribute("msgErro", "Paciente não encontrado");
			return "redirect:/paciente";
		}
		model.addAttribute("paciente", paciente);
		return "/paciente/form";
	}
}
