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

import br.com.faddvm.dao.FisioterapeutaDao;
import br.com.faddvm.model.Fisioterapeuta;
import br.com.faddvm.util.validator.FisioterapeutaValidator;

@Transactional
@Controller
@RequestMapping("/fisioterapeuta")
public class FisioterapeutaController {

	@Autowired
	@Qualifier("hibernateFisioterapeutaDao")
	FisioterapeutaDao fisioterapeutaDao;

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Fisioterapeuta fisioterapeuta, String contrasenha,
			BindingResult errors) {

		if(!fisioterapeuta.getSenha().equals(contrasenha)){
			errors.reject(null,"Contra senha deve ser igual a senha");
		}
		
		ValidationUtils.invokeValidator(new FisioterapeutaValidator(),
				fisioterapeuta, errors);

		if (errors.hasErrors()) {
			return "/fisioterapeuta/form";
		}
		fisioterapeutaDao.salvar(fisioterapeuta);

		return "redirect:/fisioterapeuta";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model) {

		model.addAttribute("fisioterapeutas", fisioterapeutaDao.lista());

		return "/fisioterapeuta/home";
	}

	@RequestMapping("/novo")
	public String novo(Model model) {

		model.addAttribute("fisioterapeuta", new Fisioterapeuta());

		return "/fisioterapeuta/form";
	}

	@RequestMapping("/{fisioterapeutaId}")
	public String mostra(@PathVariable Long fisioterapeutaId, Model model) {

		model.addAttribute("fisioterapeuta",
				fisioterapeutaDao.get(fisioterapeutaId));

		return "/fisioterapeuta/mostra";
	}

	@RequestMapping("/{fisioterapeutaId}/editar")
	public String editar(@PathVariable Long fisioterapeutaId, Model model) {

		model.addAttribute("fisioterapeuta",
				fisioterapeutaDao.get(fisioterapeutaId));

		return "/fisioterapeuta/form";
	}

}
