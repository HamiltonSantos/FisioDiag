package br.com.faddvm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.faddvm.dao.CategoriaDao;
import br.com.faddvm.dao.VariavelDao;
import br.com.faddvm.model.Categoria;
import br.com.faddvm.model.FaixaValor;
import br.com.faddvm.model.Variavel;
import br.com.faddvm.util.validator.VariavelValidator;

@Transactional
@Controller
@RequestMapping("/variavel")
public class VariavelController {

	private static final Logger logger = LoggerFactory
			.getLogger(VariavelController.class);

	@ExceptionHandler(Exception.class)
	public String handleExceptions(Exception anExc) {
		logger.error("Exception", anExc);
		return "redirect:/erro";
	}

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
	public String remover(@PathVariable Long variavelId,
			RedirectAttributes rAttributes) {

		Variavel variavel = variavelDao.get(variavelId);

		if (variavel == null) {
			rAttributes
					.addFlashAttribute("msgErro", "Variavel não Encontrada.");
			return "redirect:/categoria";
		}

		Long categoriaId = variavel.getCategoria().getId();

		List<FaixaValor> faixas = variavelDao.getFaixasByVariavel(variavel);

		if (faixas != null && faixas.size() > 0) {
			rAttributes
					.addFlashAttribute("msgErro",
							"Você não pode deletar essa Variavel, delete as faixas dela primeiro.");
			return "redirect:/categoria/" + categoriaId;
		}

		logger.info("Removendo variavel", variavel);
		variavelDao.remove(variavel);
		rAttributes.addFlashAttribute("msgSucesso", "Váriavel deletada.");

		return "redirect:/categoria/" + categoriaId;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Variavel variavel, BindingResult result,
			RedirectAttributes rAttributes) {

		Categoria categoria = categoriaDao.get(variavel.getCategoria().getId());
		variavel.setCategoria(categoria);

		ValidationUtils.invokeValidator(new VariavelValidator(variavelDao),
				variavel, result);

		if (result.hasErrors()) {
			return "/variavel/form";
		}

		logger.info("Variavel salva", variavel);
		variavelDao.salvar(variavel);
		rAttributes.addFlashAttribute("msgSucesso",
				"Variavel cadastrada com Sucesso");
		return "redirect:/categoria/" + variavel.getCategoria().getId();
	}
}
