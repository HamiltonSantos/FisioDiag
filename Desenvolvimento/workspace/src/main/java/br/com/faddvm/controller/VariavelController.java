package br.com.faddvm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.faddvm.dao.VariavelDao;
import br.com.faddvm.model.Categoria;
import br.com.faddvm.model.Variavel;

@Transactional
@Controller
public class VariavelController {

	@Autowired
	@Qualifier("hibernateVariavelDao")
	VariavelDao dao;

	@RequestMapping("/variavel/adicionaVariavel")
	public String adicionaVariavel(Categoria categoria,Variavel variavel, Model model) {

		variavel.setCategoria(categoria);
		
		dao.salvar(variavel);

		model.addAttribute("categoria", categoria);

		return "/categoria/adicionada";
	}

}
