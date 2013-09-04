package br.com.faddvm.controller;

import javassist.expr.NewArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.faddvm.dao.FisioterapeutaDao;
import br.com.faddvm.model.Fisioterapeuta;

@Transactional
@Controller
@RequestMapping("/fisioterapeuta")
public class FisioterapeutaController {

	@Autowired
	@Qualifier("hibernateFisioterapeutaDao")
	FisioterapeutaDao fisioterapeutaDao;

	@RequestMapping(method = RequestMethod.POST)
	public String adiciona(Fisioterapeuta fisioterapeuta) {

		fisioterapeutaDao.salvar(fisioterapeuta);

		return "redirect:/fisioterapeuta";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model) {

		model.addAttribute("fisioterapeutas", fisioterapeutaDao.lista());

		return "/fisioterapeuta/home";
	}

	@RequestMapping("/novo")
	public String novo(Model model) {

		model.addAttribute("fisioterapeuta", new Fisioterapeuta());
		
		return "/fisioterapeuta/form";
	}

}
