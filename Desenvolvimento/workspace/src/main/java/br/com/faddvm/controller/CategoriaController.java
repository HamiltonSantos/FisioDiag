package br.com.faddvm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.faddvm.dao.CategoriaDao;
import br.com.faddvm.dao.FaixaValorDao;
import br.com.faddvm.dao.VariavelDao;
import br.com.faddvm.model.Categoria;
import br.com.faddvm.model.FaixaValor;
import br.com.faddvm.model.Variavel;
import br.com.faddvm.util.validator.CategoriaValidator;
import br.com.faddvm.util.validator.FaixaValorValidator;
import br.com.faddvm.util.validator.VariavelValidator;

@Transactional
@Controller
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	@Qualifier("hibernateCategoriaDao")
	CategoriaDao dao;
	@Autowired
	@Qualifier("hibernateVariavelDao")
	VariavelDao daoVariavel;
	@Autowired
	@Qualifier("hibernateFaixaValorDao")
	FaixaValorDao daoFaixaValor;

	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model) {
		model.addAttribute("categorias", dao.lista());
		return "/categoria/home";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String adicionaCategoria(Categoria categoria, BindingResult result) {

		ValidationUtils.invokeValidator(new  CategoriaValidator(), categoria, result);
		
		if (result.hasErrors()) {
			return "/categoria/form";
		}
		dao.salva(categoria);

		return "redirect:/categoria/" + categoria.getId();
	}

	@RequestMapping("/nova")
	public String nova(Model model) {

		Categoria categoria = new Categoria();
		categoria.setStatus('C');

		model.addAttribute("categoria", categoria);

		return "/categoria/form";
	}

	@RequestMapping("/{categoriaId}")
	public String mostra(@PathVariable String categoriaId, Model model) {
		Categoria categoria = dao.get(new Long(categoriaId));

		model.addAttribute("categoria", categoria);
		
		model.addAttribute("variavel", new Variavel());

		return "/categoria/mostra";
	}

	@RequestMapping("/{categoriaId}/adicionaVariavel")
	public String adicionaVariavel(@PathVariable String categoriaId,
			Variavel variavel, BindingResult result) {
		
		Categoria categoria = dao.get(new Long(categoriaId));
		variavel.setCategoria(categoria);
		variavel.setStatus('A');
		
		ValidationUtils.invokeValidator(new VariavelValidator(), variavel, result);
		
		if(result.hasErrors()){
			return "redirect:/categoria/" + categoriaId;
		}
		
		daoVariavel.salvar(variavel);

		return "redirect:/categoria/" + categoriaId;
	}

	@RequestMapping("/{categoriaId}/{variavelId}/adicionaFaixa")
	public String adicionaRange(@PathVariable String categoriaId,
			@PathVariable String variavelId, FaixaValor faixaValor, BindingResult result) {

		ValidationUtils.invokeValidator(new FaixaValorValidator(), faixaValor, result);
		
		if(result.hasErrors()){
			return "redirect:/categoria/" + categoriaId;
		}
		
		Variavel variavel = daoVariavel.get(new Long(variavelId));

		if (variavel.getTipo() == 'O') {
			faixaValor.setValorMin(faixaValor.getPeso());
			faixaValor.setValorMax(faixaValor.getPeso());
		} else {
			faixaValor.setDescricao(variavel.getDescricao() + " - "
					+ faixaValor.getValorMin() + " - "
					+ faixaValor.getValorMax());
		}

		faixaValor.setVariavel(variavel);

		daoFaixaValor.salvar(faixaValor);

		return "redirect:/categoria/" + categoriaId;
	}

	@RequestMapping(value = "/ocorrencia", method = RequestMethod.GET)
	public String ocorrencia(Model model) {

		model.addAttribute("ocorrencias", daoFaixaValor.listOcorrencias());

		return "/ocorrencia/home";
	}

	@RequestMapping(value = "/ocorrencia", method = RequestMethod.POST)
	public String salvaOcorrencia(FaixaValor ocorrencia) {

		// Peso min e Max
		ocorrencia.setValorMin(ocorrencia.getPeso());
		ocorrencia.setValorMax(ocorrencia.getPeso());

		// Variavel
		// Variavel
		ocorrencia.setVariavel(daoVariavel.get(1l));
		// Insere banco
		daoFaixaValor.salvar(ocorrencia);

		return "redirect:/categoria/ocorrencia";
	}

	@RequestMapping(value = "/ocorrencia/nova", method = RequestMethod.GET)
	public String novaOcorrencia(Model model) {

		model.addAttribute("ocorrencia", new FaixaValor());
		return "/ocorrencia/form";
	}

	@RequestMapping(value = "/intercorrencia", method = RequestMethod.GET)
	public String intercorrencia(Model model) {

		model.addAttribute("intercorrencias",
				daoFaixaValor.listaIntercorrencias());

		return "/intercorrencia/home";
	}

	@RequestMapping(value = "/intercorrencia", method = RequestMethod.POST)
	public String salvaIntercorrencia(FaixaValor intercorrencia) {

		// Peso min e Max
		intercorrencia.setValorMin(intercorrencia.getPeso());
		intercorrencia.setValorMax(intercorrencia.getPeso());

		// Variavel
		// Variavel
		intercorrencia.setVariavel(daoVariavel.get(2l));
		// Insere banco
		daoFaixaValor.salvar(intercorrencia);

		return "redirect:/categoria/intercorrencia";
	}

	@RequestMapping(value = "/intercorrencia/nova", method = RequestMethod.GET)
	public String novaIntercorrencia(Model model) {

		model.addAttribute("intercorrencia", new FaixaValor());
		return "/intercorrencia/form";
	}

	@RequestMapping(value = "/indice", method = RequestMethod.GET)
	public String indice(Model model) {

		model.addAttribute("indices", daoFaixaValor.listaIndices());
		return "/indice/home";
	}

	@RequestMapping(value = "/indice", method = RequestMethod.POST)
	public String salvaIndice(@ModelAttribute("indice") FaixaValor faixa, BindingResult result) {
		ValidationUtils.invokeValidator(new FaixaValorValidator(), faixa, result);
		
		if (result.hasErrors()) {
			return "/indice/form";
		}

		faixa.setVariavel(daoVariavel.get(3l));
		daoFaixaValor.salvar(faixa);
		return "redirect:/categoria/indice";
	}

	@RequestMapping(value = "/indice/novo", method = RequestMethod.GET)
	public String novoIndice(Model model) {

		FaixaValor faixa = new FaixaValor();
		faixa.setPeso(0);
		faixa.setValorMin(daoFaixaValor.getValorMinIndice());

		model.addAttribute("indice", faixa);
		return "/indice/form";
	}
}
