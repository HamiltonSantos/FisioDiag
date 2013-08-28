package br.com.faddvm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.faddvm.dao.CategoriaDao;
import br.com.faddvm.dao.FaixaValorDao;
import br.com.faddvm.dao.VariavelDao;
import br.com.faddvm.model.Categoria;
import br.com.faddvm.model.FaixaValor;
import br.com.faddvm.model.Variavel;

@Transactional
@Controller
public class CategoriaController {

	@Autowired
	@Qualifier("hibernateCategoriaDao")
	CategoriaDao dao;
	@Autowired
	@Qualifier("hibernateVariavelDao")
	VariavelDao daoVariavel;
	@Autowired
	@Qualifier("hibernateFaixaValorDao")
	FaixaValorDao daoFaixaValor;

	@RequestMapping("/categoria/adicionaCategoria")
	public String adicionaCategoria(Categoria categoria) {

		dao.salva(categoria);

		return "redirect:/categoria/" + categoria.getId();
	}

	@RequestMapping("/categoria/nova")
	public String nova() {
		return "/categoria/nova";
	}

	@RequestMapping("/categoria/{categoriaId}")
	public String mostra(@PathVariable String categoriaId, Model model) {
		Categoria categoria = dao.get(new Long(categoriaId));

		model.addAttribute("categoria", categoria);

		return "/categoria/mostra";
	}

	@RequestMapping("/categoria/{categoriaId}/adicionaVariavel")
	public String adicionaVariavel(@PathVariable String categoriaId,
			Variavel variavel) {
		Categoria categoria = dao.get(new Long(categoriaId));

		variavel.setCategoria(categoria);

		daoVariavel.salvar(variavel);

		return "redirect:/categoria/" + categoriaId;
	}

	@RequestMapping("/categoria/{categoriaId}/{variavelId}/adicionaFaixa")
	public String adicionaRange(@PathVariable String categoriaId,
			@PathVariable String variavelId, FaixaValor faixaValor) {

		Variavel variavel = daoVariavel.get(new Long(variavelId));

		faixaValor.setVariavel(variavel);

		daoFaixaValor.salvar(faixaValor);

		return "redirect:/categoria/" + categoriaId;
	}

}
