package br.com.faddvm.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.faddvm.dao.FisioterapeutaDao;
import br.com.faddvm.dao.PacienteDao;
import br.com.faddvm.model.Fisioterapeuta;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@ExceptionHandler(Exception.class)
	public String handleExceptions(Exception anExc) {
		logger.error("Exception", anExc);
		return "redirect:/erro";
	}

	@Autowired
	@Qualifier("hibernateFisioterapeutaDao")
	FisioterapeutaDao fisioterapeutaDao;

	@Autowired
	@Qualifier("hibernatePacienteDao")
	PacienteDao pacienteDao;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLogin(Model model) {
		model.addAttribute("fisioterapeuta", new Fisioterapeuta());
		return "/login/home";
	}

	@RequestMapping(value = "/teste", method = RequestMethod.GET)
	public String teste() {
		return "teste";
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
			logger.info("Fisioterapeuta Login", loginFisioterapeuta);
			session.setAttribute("fisioterapeutaLogado", loginFisioterapeuta);
			return "redirect:/";
		}
		return "/login/home";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {

		model.addAttribute("pacientesVM", pacienteDao.getPacientesVM());

		model.addAttribute("pacientesDesmame",
				pacienteDao.getPacientesDesmame());

		model.addAttribute("pacientesExtubados",
				pacienteDao.getPacientesExtubados());

		model.addAttribute("pacientesReintubados",
				pacienteDao.getPacientesReintubados());

		model.addAttribute("atendimentosRealizados",
				pacienteDao.getUltimosAtendimentos());

		model.addAttribute("pacientesUTI", pacienteDao.getPacientesUTI());
		return "home";
	}

	@RequestMapping(value = "/erro", method = RequestMethod.GET)
	public String erro() {
		logger.info("Erro", new Date());
		return "erro";
	}

}
