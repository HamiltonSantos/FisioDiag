package br.com.faddvm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.faddvm.dao.CategoriaDao;
import br.com.faddvm.dao.VariavelDao;
import br.com.faddvm.model.Categoria;
import br.com.faddvm.model.Variavel;
import br.com.faddvm.util.validator.VariavelValidator;

@Transactional
@Controller
@RequestMapping("/variavel")
public class VariavelController {

	@Autowired
	@Qualifier("hibernateCategoriaDao")
	CategoriaDao categoriaDao;

	@Autowired
	@Qualifier("hibernateVariavelDao")
	VariavelDao variavelDao;

	@RequestMapping(value = "/{variavelId}", method = RequestMethod.GET)
	public String mostra(@PathVariable Long variavelId, Model model) {

		Variavel variavel = variavelDao.get(variavelId);

		model.addAttribute("variavel", variavel);

		return "/variavel/mostra";
	}

	@RequestMapping(value = "/nova/{categoriaId}", method = RequestMethod.GET)
	public String nova(@PathVariable Long categoriaId, Model model) {

		Categoria categoria = categoriaDao.get(categoriaId);
		model.addAttribute("categoria", categoria);
		model.addAttribute("variavel", new Variavel());

		return "/variavel/form";
	}

	@RequestMapping(value = "/{categoriaId}", method = RequestMethod.POST)
	public String salvar(@PathVariable Long categoriaId, Variavel variavel,
			Model model, BindingResult result) {

		Categoria categoria = categoriaDao.get(categoriaId);
		variavel.setCategoria(categoria);
		variavel.setStatus('A');

		ValidationUtils.invokeValidator(new VariavelValidator(), variavel,
				result);

		if (result.hasErrors()) {
			model.addAttribute("categoria", categoria);
			return "/variavel/form";
		}

		variavelDao.salvar(variavel);

		return "redirect:/categoria/" + categoriaId;
	}
}
