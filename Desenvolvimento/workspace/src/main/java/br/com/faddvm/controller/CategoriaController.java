package br.com.faddvm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.faddvm.dao.CategoriaDao;
import br.com.faddvm.model.Categoria;

@Transactional
@Controller
public class CategoriaController {

	@Autowired
	@Qualifier("hibernateCategoriaDao")
	CategoriaDao dao;

	@RequestMapping("/categoria/adicionaCategoria")
	public String adicionaCategoria(Categoria categoria, Model model) {

		dao.salva(categoria);

		model.addAttribute("categoria", categoria);

		return "/categoria/adicionada";
	}

	@RequestMapping("/categoria/nova")
	public String nova() {
		return "/categoria/nova";
	}

}
