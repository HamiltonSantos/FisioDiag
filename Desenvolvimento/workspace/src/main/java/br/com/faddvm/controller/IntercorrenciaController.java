package br.com.faddvm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.faddvm.dao.FaixaValorDao;
import br.com.faddvm.dao.VariavelDao;
import br.com.faddvm.model.FaixaValor;

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
	public String salvar(FaixaValor intercorrencia) {

		// Peso min e Max
		intercorrencia.setValorMin(intercorrencia.getPeso());
		intercorrencia.setValorMax(intercorrencia.getPeso());

		// Variavel
		intercorrencia.setVariavel(variavelDao.get(idIntercorrencia));
		// Insere banco
		faixaValorDao.salvar(intercorrencia);

		return "redirect:/intercorrencia";
	}

	@RequestMapping(value = "/nova", method = RequestMethod.GET)
	public String nova(Model model) {

		model.addAttribute("intercorrencia", new FaixaValor());
		return "/intercorrencia/form";
	}

}
