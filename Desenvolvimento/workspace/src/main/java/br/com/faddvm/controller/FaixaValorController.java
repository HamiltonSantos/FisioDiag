package br.com.faddvm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.faddvm.dao.FaixaValorDao;
import br.com.faddvm.dao.VariavelDao;
import br.com.faddvm.model.FaixaValor;
import br.com.faddvm.model.Variavel;

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

	@RequestMapping(value = "/{variavelId}", method = RequestMethod.POST)
	public String salvar(@PathVariable Long variavelId, FaixaValor faixaValor,
			BindingResult errors) {

		Variavel variavel = variavelDao.get(variavelId);

		preencheFaixaValor(faixaValor, variavel);

		if (errors.hasErrors()) {
			return "/faixaValor/form";
		}

		faixaValorDao.salvar(faixaValor);

		return "redirect:/variavel/" + variavel.getId();
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
