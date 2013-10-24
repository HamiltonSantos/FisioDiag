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
@RequestMapping("/indice")
public class IndiceController {

	@Autowired
	@Qualifier("hibernateFaixaValorDao")
	FaixaValorDao faixaValorDao;

	@Autowired
	@Qualifier("hibernateVariavelDao")
	VariavelDao variavelDao;

	Long idIndice = 3l;

	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model) {

		model.addAttribute("indices", faixaValorDao.listaIndices());
		return "/indice/home";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@ModelAttribute("indice") FaixaValor faixa,
			BindingResult result, RedirectAttributes rAttributes) {

		faixa.setPeso(0);
		faixa.setVariavel(variavelDao.get(idIndice));

		ValidationUtils.invokeValidator(new FaixaValorValidator(), faixa,
				result);

		if (result.hasErrors()) {
			return "/indice/form";
		}

		faixaValorDao.salvar(faixa);
		rAttributes.addFlashAttribute("msgSucesso",
				"Indice cadastrado com Sucesso");
		return "redirect:/indice";
	}

	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public String novo(Model model) {

		model.addAttribute("indice", new FaixaValor());
		return "/indice/form";
	}

}
