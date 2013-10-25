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

import br.com.faddvm.dao.FaixaValorDao;
import br.com.faddvm.dao.VariavelDao;
import br.com.faddvm.model.FaixaValor;
import br.com.faddvm.model.Variavel;
import br.com.faddvm.util.validator.FaixaValorValidator;

@Transactional
@Controller
@RequestMapping("/faixaValor")
public class FaixaValorController {

	@Autowired
	@Qualifier("hibernateFaixaValorDao")
	FaixaValorDao faixaValorDao;

	@Autowired
	@Qualifier("hibernateVariavelDao")
	VariavelDao variavelDao;

	@RequestMapping(value = "/nova/{variavelId}", method = RequestMethod.GET)
	public String nova(@PathVariable Long variavelId, Model model) {

		FaixaValor faixaValor = new FaixaValor();

		Variavel variavel = variavelDao.get(variavelId);
		faixaValor.setVariavel(variavel);

		model.addAttribute("faixaValor", faixaValor);

		return "/faixaValor/form";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(FaixaValor faixaValor, BindingResult errors,
			RedirectAttributes rAttributes) {

		Variavel variavel = variavelDao.get(faixaValor.getVariavel().getId());

		preencheFaixaValor(faixaValor, variavel);

		ValidationUtils.invokeValidator(new FaixaValorValidator(), faixaValor,
				errors);
		if (errors.hasErrors()) {
			return "/faixaValor/form";
		}

		faixaValorDao.salvar(faixaValor);

		rAttributes.addFlashAttribute("msgSucesso",
				"Faixa cadastrada com Sucesso");
		return "redirect:/variavel/" + variavel.getId();
	}

	@RequestMapping(value = "/remover/{faixaId}")
	public String remover(@PathVariable Long faixaId) {
		FaixaValor faixaValor = faixaValorDao.get(faixaId);

		Long idVariavel = faixaValor.getVariavel().getId();

		faixaValorDao.remover(faixaValor);
		return "redirect:/variavel/" + idVariavel;
	}

	private FaixaValor preencheFaixaValor(FaixaValor faixaValor,
			Variavel variavel) {
		if (variavel.getTipo() == 'O') {
			faixaValor.setValorMin(faixaValor.getPeso());
			faixaValor.setValorMax(faixaValor.getPeso());
		} else {
			faixaValor.setDescricao(variavel.getDescricao() + " - "
					+ faixaValor.getValorMin() + " - "
					+ faixaValor.getValorMax());
		}
		faixaValor.setVariavel(variavel);
		return faixaValor;
	}

}
