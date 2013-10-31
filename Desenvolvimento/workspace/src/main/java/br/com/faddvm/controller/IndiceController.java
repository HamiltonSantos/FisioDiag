package br.com.faddvm.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

	private static final Logger logger = LoggerFactory
			.getLogger(IndiceController.class);

	@ExceptionHandler(Exception.class)
	public String handleExceptions(Exception anExc) {
		logger.error("Exception", anExc);
		return "redirect:/erro";
	}

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

		ValidationUtils.invokeValidator(new FaixaValorValidator(faixaValorDao),
				faixa, result);

		if (result.hasErrors()) {
			return "/indice/form";
		}

		logger.info("Indice Salvo", faixa);
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

	@RequestMapping(value = "/remover/{indiceId}", method = RequestMethod.GET)
	public String remover(@PathVariable Long indiceId,
			RedirectAttributes rAttributes) {

		FaixaValor indice = faixaValorDao.get(indiceId);

		if (indice == null) {
			rAttributes.addFlashAttribute("msgErro", "Indice não Encontrada.");
			return "redirect:/indice";
		}

		FaixaValor faixaMenor = faixaValorDao.getByValor(
				new Long(indice.getValorMin() - 1), indice.getVariavel()
						.getId());
		FaixaValor faixaMaior = faixaValorDao.getByValor(
				new Long(indice.getValorMax() + 1), indice.getVariavel()
						.getId());

		if (faixaMenor != null && faixaMaior != null) {
			rAttributes
					.addFlashAttribute("msgErro",
							"Você não pode deletar esse Indice pois ele deixaria Furo.");
			return "redirect:/indice";
		}
		
		logger.info("Removendo Indice.", indice);
		faixaValorDao.remover(indice);

		rAttributes.addFlashAttribute("msgSucesso", "Indice removido.");

		return "redirect:/indice";
	}

}
