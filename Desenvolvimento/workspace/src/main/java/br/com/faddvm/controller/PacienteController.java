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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		
		binder.addValidators(new PacienteValidator());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model) {
		model.addAttribute("pacientes", dao.lista());
		return "/paciente/home";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String add(@Valid Paciente paciente, BindingResult result) {
		
		if (result.hasErrors()) {
			return "/paciente/form";
		}
		
		if(paciente.getId() == null){
			dao.salvar(paciente);
		}else{
			dao.atualiza(paciente);
		}
		
		return "redirect:/paciente";
	}

	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public String novo(Model model) {
		model.addAttribute("paciente", new Paciente());
		return "/paciente/form";
	}
	
	@RequestMapping(value="/{pacienteId}", method = RequestMethod.GET)
	public String mostra(@PathVariable Long pacienteId,Model model){
		
		model.addAttribute("paciente",dao.get(pacienteId));
		return "/paciente/mostra";
	}
	
	@RequestMapping(value="/{pacienteId}/editar", method = RequestMethod.GET)
	public String editar(@PathVariable Long pacienteId, Model model){
		
		model.addAttribute("paciente", dao.get(pacienteId));
		return "/paciente/form";
	}
}
