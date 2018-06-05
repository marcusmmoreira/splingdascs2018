package br.univille.ProjetoDasc2018.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.univille.ProjetoDasc2018.projetospring.model.Medico;
import br.univille.ProjetoDasc2018.projetospring.repository.MedicoRepository;


@Controller
@RequestMapping("/medico")
public class MedicoController {
	
	@Autowired
	private MedicoRepository medicoRepository;

    @GetMapping("")
    public ModelAndView index() {

        List<Medico> listaMedico = this.medicoRepository.findAll();
        
        return new ModelAndView("medico/index","listamed",listaMedico);
    }
    
    @GetMapping("/novo")
    public String createForm(@ModelAttribute Medico medico) {
        return "medico/form";
    }
    
    
    @PostMapping(params="form")
    public ModelAndView save(@Valid Medico medico, BindingResult result, RedirectAttributes redirect) {
        
    	medico = this.medicoRepository.save(medico);
        
        return new ModelAndView("redirect:/medico");
    }
    
    @GetMapping(value="/alterar/{id}")
    public ModelAndView alterarForm(@PathVariable("id") Medico medico) {
        return new ModelAndView("medico/form","medico",medico);
    }
    
    @GetMapping(value="remover/{id}")
    public ModelAndView remover(@PathVariable ("id") Long id, RedirectAttributes redirect) {
        this.medicoRepository.deleteById(id);
        return new ModelAndView("redirect:/medico");
    }

    
}