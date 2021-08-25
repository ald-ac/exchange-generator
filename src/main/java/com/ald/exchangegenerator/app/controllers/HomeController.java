package com.ald.exchangegenerator.app.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ald.exchangegenerator.app.models.domain.Contestant;
import com.ald.exchangegenerator.app.services.ContestantService;
import com.ald.exchangegenerator.app.services.ContestantServiceImpl;
import com.ald.exchangegenerator.app.services.MailService;

@Controller
@SessionAttributes({"contestants", "contestant"})
public class HomeController {
	
	@Autowired
	MailService mailservice;
	
	@ModelAttribute("contestants")
	public ContestantService contestants() {
		return new ContestantServiceImpl();
	}
	
	@GetMapping("/")
	public String home(@ModelAttribute(name = "contestants") ContestantService contestants) {
		contestants.clear();
		return "home";
	}
	
	@GetMapping({"/generator", "/generator/{id}"})
	public String generator(@PathVariable(required = false) String id,Model model, @ModelAttribute(name = "contestants") ContestantService contestants) {
		Contestant contestant;
		String messageBtn;
		if(id != null) {
			contestant = contestants.findById(id);
			messageBtn = "Modificar participante";
		} else {
			contestant = new Contestant();
			messageBtn = "Agregar participante +";
		}
		model.addAttribute("contestant", contestant);
		model.addAttribute("messageBtn", messageBtn);
		return "generator";
	}
	
	@PostMapping("/addContestant")
	public String addContestant(@ModelAttribute Contestant contestant, @ModelAttribute(name = "contestants") ContestantService contestants) {
		if(contestant.getId() == null) {
			System.out.println(contestant.getId());
			contestant.setId(LocalDateTime.now().toString());
			contestants.add(contestant);
		} else {
			contestants.update(contestant);
		}
		return "redirect:/generator";
	}
	
	@GetMapping("/removeContestant/{id}")
	public String removeContestant(@PathVariable(name = "id") String id, @ModelAttribute(name = "contestants") ContestantService contestants) {
		contestants.removeIf(id);
		return "redirect:/generator";
	}
	
	@GetMapping("/generate")
	public String generateExchange(@ModelAttribute(name = "contestants") ContestantService contestants, RedirectAttributes flash) {
		if(contestants.size() < 3) {
			flash.addFlashAttribute("error", "Para generar un intercambio debe haber como mínimo 3 participantes");
			return "redirect:/generator";
		} else {
			contestants.randomSort();
			if(mailservice.sendMails(contestants.list())) {
				flash.addFlashAttribute("success", "El intercambio se ha generado exitosamente, cada participante tiene otro asignado para darle un regalo");
			} else {
				flash.addFlashAttribute("error", "El intercambio no pudo llevarse a cabo, revise que los correos electrónicos son correctos");
			}
			return "redirect:/";
		}
	}
}
