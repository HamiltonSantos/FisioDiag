package br.com.faddvm.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.faddvm.dao.CategoriaDao;
import br.com.faddvm.dao.FaixaValorDao;
import br.com.faddvm.dao.PacienteDao;
import br.com.faddvm.dao.VariavelDao;
import br.com.faddvm.model.FaixaValor;
import br.com.faddvm.model.Fisioterapeuta;
import br.com.faddvm.model.Historico;
import br.com.faddvm.model.Paciente;
import br.com.faddvm.model.ValorAtendimento;
import br.com.faddvm.model.Variavel;

@Controller
@RequestMapping("/atendimento/{pacienteId}")
@Transactional
public class AtendimentoController {

	@Autowired
	@Qualifier("hibernatePacienteDao")
	PacienteDao pacienteDao;

	@Autowired
	@Qualifier("hibernateCategoriaDao")
	CategoriaDao categoriaDao;

	@Autowired
	@Qualifier("hibernateVariavelDao")
	VariavelDao variavelDao;
	
	@Autowired
	@Qualifier("hibernateFaixaValorDao")
	FaixaValorDao faixaValorDao;

	@RequestMapping(method = RequestMethod.GET)
	public String get(@PathVariable Long pacienteId, Model model) {

		Paciente paciente = pacienteDao.get(pacienteId);
		model.addAttribute("paciente", paciente);

		model.addAttribute("categorias", categoriaDao.lista());

		model.addAttribute("ocorrencias", faixaValorDao.listOcorrencias());
		
		model.addAttribute("intercorrencias", faixaValorDao.listaIntercorrencias());
		
		model.addAttribute("indicacao", "Indicação não encontrada.");
		
		for(FaixaValor f : faixaValorDao.listaIndices()) {
			if(paciente.getPontos() >= f.getValorMin() && paciente.getPontos() <= f.getValorMax()) {
				model.addAttribute("indicacao", f.getDescricao());
			}
		}
		
		return "/atendimento/home";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@PathVariable Long pacienteId,
			ValorAtendimento valorAtendimento, HttpSession session) {
		System.out.println(valorAtendimento.getValor() + "Valor - ID"
				+ valorAtendimento.getVariavelId());

		Paciente paciente = pacienteDao.get(pacienteId);

		Historico historico = new Historico();
		// SetarData
		historico.setData(new Date());
		// SetarFisioterapeuta
		historico.setFisioterapeuta((Fisioterapeuta) session
				.getAttribute("fisioterapeutaLogado"));
		// SetarPaciente
		historico.setPaciente(paciente);
		// SetarVariavel
		Variavel variavel = variavelDao.get(valorAtendimento.getVariavelId());
		historico.setVariavel(variavel);
		// SetarValor
		historico.setValor(valorAtendimento.getValor());

		// Falta verificar se ja foi adicionado uma variavel igual para nao
		// somar 2 vezes
		// Atualiza a pontuacao do paciente
		int pontos = paciente.getPontos();
		// Se esta inserindo uma Opcao
		if (variavel.getTipo() == 'O') {

			pontos += valorAtendimento.getValor();
			paciente.setPontos(pontos);
			System.out.println("adicionado range peso: "
					+ valorAtendimento.getValor());
			// Se for Range
		} else if (variavel.getTipo() == 'R') {
			for (FaixaValor f : variavel.getFaixaValores()) {
				if (valorAtendimento.getValor() > f.getValorMin()
						&& valorAtendimento.getValor() < f.getValorMax()) {
					pontos += f.getPeso();
					paciente.setPontos(pontos);
					System.out.println("adicionado range peso: " + f.getPeso());
				}
			}
		}

		// Adiciona o historico no paciente
		paciente.getHistorico().add(historico);

		// Atualiza no banco
		pacienteDao.atualiza(paciente);
		// //Atualizar Indicação do Paciente
		return "redirect:/atendimento/" + pacienteId;
	}
}
