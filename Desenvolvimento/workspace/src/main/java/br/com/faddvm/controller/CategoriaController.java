package br.com.faddvm.controller;

import javax.validation.Valid;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.faddvm.dao.CategoriaDao;
import br.com.faddvm.model.Categoria;
import br.com.faddvm.util.validator.CategoriaValidator;

@Transactional
@Controller
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	@Qualifier("hibernateCategoriaDao")
	CategoriaDao categoriaDao;

	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("categorias", categoriaDao.lista());
		return "/categoria/home";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Valid Categoria categoria, BindingResult result,
			RedirectAttributes rAttributes) {

		ValidationUtils.invokeValidator(new CategoriaValidator(), categoria,
				result);

		if (result.hasErrors()) {
			return "/categoria/form";
		}
		categoriaDao.salvar(categoria);
		rAttributes.addFlashAttribute("msgSucesso",
				"Categoria cadastrada com Sucesso");
		return "redirect:/categoria/" + categoria.getId();
	}

	@RequestMapping("/nova")
	public String nova(Model model) {

		Categoria categoria = new Categoria();
		categoria.setStatus('C');

		model.addAttribute("categoria", categoria);

		return "/categoria/form";
	}

	@RequestMapping(value = "/{categoriaId}", method = RequestMethod.GET)
	public String mostra(@PathVariable Long categoriaId, Model model,
			RedirectAttributes rAttributes) {
		Categoria categoria = categoriaDao.get(categoriaId);

		if (categoria == null) {
			rAttributes.addFlashAttribute("msgErro",
					"Categoria não encontrada.");
			return "redirect:/categoria";
		}

		model.addAttribute("categoria", categoria);

		return "/categoria/mostra";
	}
}
