package br.com.faddvm.controller;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.faddvm.dao.FisioterapeutaDao;
import br.com.faddvm.model.Fisioterapeuta;

@Transactional
@Controller
public class FisioterapeutaController {

	@Autowired
	@Qualifier("hibernateFisioterapeutaDao")
	FisioterapeutaDao fisioterapeutaDao;

	@RequestMapping("/fisioterapeuta/adicionaFisioterapeuta")
	public String adiciona(Fisioterapeuta fisioterapeuta, Model model) {

		fisioterapeutaDao.salvar(fisioterapeuta);

		model.addAttribute("fisioterapeuta", fisioterapeuta);
		
		return "fisioterapeuta/adicionado";
	}
	
	@RequestMapping("/fisioterapeuta/novo")
	public String novo() {
		return "fisioterapeuta/novo";
	}

}
