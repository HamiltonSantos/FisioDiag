package br.com.faddvm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/ocorrencia")
public class OcorrenciaController {

	@RequestMapping(method=RequestMethod.GET)
	public String get() {
		return "/ocorrencia/home";
	}
	
}
