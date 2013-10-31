package br.com.faddvm.controller;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.faddvm.dao.FaixaValorDao;
import br.com.faddvm.dao.VariavelDao;
import br.com.faddvm.model.FaixaValor;
import br.com.faddvm.util.validator.FaixaValorValidator;

@Transactional
@Controller
@RequestMapping("/ocorrencia")
public class OcorrenciaController {

	private static final Logger logger = LoggerFactory
			.getLogger(OcorrenciaController.class);

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

	Long idOcorrencia = 1l;

	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model) {

		model.addAttribute("ocorrencias", faixaValorDao.listOcorrencias());

		return "/ocorrencia/home";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(
			@Valid @ModelAttribute("ocorrencia") FaixaValor ocorrencia,
			BindingResult errors, RedirectAttributes rAttributes) {
		ocorrencia.setPeso(0);
		ocorrencia.setValorMin(ocorrencia.getPeso());
		ocorrencia.setValorMax(ocorrencia.getPeso());

		ocorrencia.setVariavel(variavelDao.get(idOcorrencia));

		ValidationUtils.invokeValidator(new FaixaValorValidator(faixaValorDao),
				ocorrencia, errors);

		if (errors.hasErrors()) {
			return "/ocorrencia/form";
		}

		// Insere banco
		logger.info("Ocorrencia salva", ocorrencia);
		faixaValorDao.salvar(ocorrencia);
		rAttributes.addFlashAttribute("msgSucesso",
				"Ocorrência cadastrada com Sucesso");
		return "redirect:/ocorrencia";
	}

	@RequestMapping(value = "/nova", method = RequestMethod.GET)
	public String nova(Model model) {

		model.addAttribute("ocorrencia", new FaixaValor());
		return "/ocorrencia/form";
	}

}
