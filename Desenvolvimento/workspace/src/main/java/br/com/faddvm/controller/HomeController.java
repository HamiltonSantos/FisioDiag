package br.com.faddvm.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.faddvm.dao.FisioterapeutaDao;
import br.com.faddvm.model.Fisioterapeuta;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	@Qualifier("hibernateFisioterapeutaDao")
	FisioterapeutaDao fisioterapeutaDao;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLogin(Model model) {
		model.addAttribute("fisioterapeuta", new Fisioterapeuta());
		return "/login/home";
	}
	
	@RequestMapping(value = "/sair", method = RequestMethod.GET)
	public String sair(HttpSession session) {
		session.setAttribute("fisioterapeutaLogado", null);
		return "redirect:/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Fisioterapeuta fisioterapeuta, HttpSession session) {
		Fisioterapeuta loginFisioterapeuta = fisioterapeutaDao
				.validaLogin(fisioterapeuta);
		if (loginFisioterapeuta != null) {
			session.setAttribute("fisioterapeutaLogado", loginFisioterapeuta);
			return "redirect:/";
		}
		return "/login/home";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}

}
