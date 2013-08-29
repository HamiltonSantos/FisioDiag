package br.com.faddvm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.faddvm.dao.PacienteDao;
import br.com.faddvm.model.Paciente;

@Transactional
@Controller
@RequestMapping("/pacientes")
public class PacienteController {

	@Autowired
	@Qualifier("hibernatePacienteDao")
	PacienteDao dao;

	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model) {
		model.addAttribute("pacientes", dao.lista());
		return "/paciente/home";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String add(Paciente paciente) {
		dao.salvar(paciente);
		return "redirect:/pacientes";
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public String novo(Model model) {
		model.addAttribute("paciente", new Paciente());
		return "/paciente/form";
	}
}
