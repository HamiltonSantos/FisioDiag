package br.com.faddvm.controller;

import java.util.List;

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
import br.com.faddvm.model.Historico;
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
	public String nova(@PathVariable Long variavelId, Model model,
			RedirectAttributes rAttributes) {

		FaixaValor faixaValor = new FaixaValor();

		Variavel variavel = variavelDao.get(variavelId);

		if (variavel == null) {
			rAttributes
					.addFlashAttribute("msgErro", "Variavel não Encontrada.");
			return "redirect:/categoria";
		}

		faixaValor.setVariavel(variavel);

		model.addAttribute("faixaValor", faixaValor);

		return "/faixaValor/form";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(FaixaValor faixaValor, BindingResult errors,
			RedirectAttributes rAttributes) {

		Variavel variavel = variavelDao.get(faixaValor.getVariavel().getId());

		preencheFaixaValor(faixaValor, variavel);

		ValidationUtils.invokeValidator(new FaixaValorValidator(faixaValorDao),
				faixaValor, errors);
		if (errors.hasErrors()) {
			return "/faixaValor/form";
		}

		faixaValorDao.salvar(faixaValor);

		rAttributes.addFlashAttribute("msgSucesso",
				"Faixa cadastrada com Sucesso");
		return "redirect:/variavel/" + variavel.getId();
	}

	@RequestMapping(value = "/remover/{faixaId}")
	public String remover(@PathVariable Long faixaId,
			RedirectAttributes rAttributes) {

		FaixaValor faixaValor = faixaValorDao.get(faixaId);

		if (faixaValor == null) {
			rAttributes.addFlashAttribute("msgErro", "Faixa não Encontrada.");
			return "redirect:/categoria";
		}
		Long idVariavel = faixaValor.getVariavel().getId();
		List<Historico> historicoFaixa = faixaValorDao
				.getHistoricoByFaixa(faixaValor);

		if (historicoFaixa != null && historicoFaixa.size() > 0) {
			rAttributes.addFlashAttribute("msgErro",
					"Você não pode deletar essa Faixa. Ela ja foi usada.");
			return "redirect:/variavel/"+idVariavel;
		}

		if (faixaValor.getVariavel().getTipo() == 'R') {
			FaixaValor faixaMenor = faixaValorDao.getByValor(new Long(
					faixaValor.getValorMin() - 1), faixaValor.getVariavel()
					.getId());
			FaixaValor faixaMaior = faixaValorDao.getByValor(new Long(
					faixaValor.getValorMax() + 1), faixaValor.getVariavel()
					.getId());

			if (faixaMenor != null && faixaMaior != null) {
				rAttributes
						.addFlashAttribute("msgErro",
								"Você não pode deletar essa Faixa pois ela deixaria Furo.");
				return "redirect:/variavel/"+idVariavel;
			}
		}

		faixaValorDao.remover(faixaValor);

		rAttributes.addFlashAttribute("msgSucesso", "Faixa removida.");

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
