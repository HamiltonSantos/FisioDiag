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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String mostra(@PathVariable Long variavelId, Model model,
			RedirectAttributes rAttributes) {

		Variavel variavel = variavelDao.get(variavelId);

		if (variavel == null) {
			rAttributes
					.addFlashAttribute("msgErro", "Variavel não Encontrada.");
			return "redirect:/categoria";
		}

		model.addAttribute("variavel", variavel);

		return "/variavel/mostra";
	}

	@RequestMapping(value = "/nova/{categoriaId}", method = RequestMethod.GET)
	public String nova(@PathVariable Long categoriaId, Model model,
			RedirectAttributes rAttributes) {

		Categoria categoria = categoriaDao.get(categoriaId);

		if (categoria == null) {
			rAttributes.addFlashAttribute("msgErro",
					"Categoria não Encontrada.");
			return "redirect:/categoria";
		}

		Variavel variavel = new Variavel();
		variavel.setCategoria(categoria);
		variavel.setStatus('A');

		model.addAttribute("variavel", variavel);

		return "/variavel/form";
	}

	@RequestMapping(value = "/remover/{variavelId}")
	public String remover(@PathVariable Long variavelId) {

		Variavel variavel = variavelDao.get(variavelId);

		Long categoriaId = variavel.getCategoria().getId();

		variavelDao.remove(variavel);

		return "redirect:/categoria/" + categoriaId;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Variavel variavel, BindingResult result,
			RedirectAttributes rAttributes) {

		Categoria categoria = categoriaDao.get(variavel.getCategoria().getId());
		variavel.setCategoria(categoria);

		ValidationUtils.invokeValidator(new VariavelValidator(), variavel,
				result);

		if (result.hasErrors()) {
			return "/variavel/form";
		}

		variavelDao.salvar(variavel);
		rAttributes.addFlashAttribute("msgSucesso",
				"Variavel cadastrada com Sucesso");
		return "redirect:/categoria/" + variavel.getCategoria().getId();
	}
}
