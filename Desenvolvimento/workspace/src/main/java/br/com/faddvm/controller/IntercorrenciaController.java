package br.com.faddvm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.faddvm.dao.FaixaValorDao;
import br.com.faddvm.dao.VariavelDao;
import br.com.faddvm.model.FaixaValor;
import br.com.faddvm.util.validator.FaixaValorValidator;

@Transactional
@Controller
@RequestMapping("/intercorrencia")
public class IntercorrenciaController {

	@Autowired
	@Qualifier("hibernateFaixaValorDao")
	FaixaValorDao faixaValorDao;

	@Autowired
	@Qualifier("hibernateVariavelDao")
	VariavelDao variavelDao;

	Long idIntercorrencia = 2l;

	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model) {

		model.addAttribute("intercorrencias",
				faixaValorDao.listaIntercorrencias());

		return "/intercorrencia/home";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(
			@ModelAttribute("intercorrencia") FaixaValor intercorrencia,
			BindingResult errors, RedirectAttributes rAttributes) {

		intercorrencia.setValorMin(intercorrencia.getPeso());
		intercorrencia.setValorMax(intercorrencia.getPeso());

		intercorrencia.setVariavel(variavelDao.get(idIntercorrencia));

		ValidationUtils.invokeValidator(new FaixaValorValidator(faixaValorDao),
				intercorrencia, errors);

		if (errors.hasErrors()) {
			return "/intercorrencia/form";
		}

		faixaValorDao.salvar(intercorrencia);
		rAttributes.addFlashAttribute("msgSucesso",
				"Indice cadastrado com Sucesso");
		return "redirect:/intercorrencia";
	}

	@RequestMapping(value = "/nova", method = RequestMethod.GET)
	public String nova(Model model) {

		model.addAttribute("intercorrencia", new FaixaValor());
		return "/intercorrencia/form";
	}

}
