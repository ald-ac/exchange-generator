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
@SessionAttributes({"contestants"})
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
	
	@GetMapping("/generator")
	public String generator(Model model) {
		model.addAttribute("contestant", new Contestant());
		return "generator";
	}
	
	@PostMapping("/addContestant")
	public String addContestant(@ModelAttribute Contestant contestant, @ModelAttribute(name = "contestants") ContestantService contestants) {
		contestant.setId(LocalDateTime.now().toString());
		contestants.add(contestant);
		return "redirect:/generator";
	}
	
	@GetMapping("/removeContestant/{id}")
	public String removeContestant(@PathVariable(name = "id") String id, @ModelAttribute(name = "contestants") ContestantService contestants) {
		contestants.removeIf(id);
		return "redirect:/generator";
	}
	
	@GetMapping("/generate")
	public String generateExchange(@ModelAttribute(name = "contestants") ContestantService contestants, RedirectAttributes flash) {
		//flash.addFlashAttribute("message", "El intercambio se ha generado exitosamente, cada participante tiene otro asignado para darle un regalo");
		contestants.randomSort();
		if(mailservice.sendMails(contestants.list())) {
			flash.addFlashAttribute("success", "El intercambio se ha generado exitosamente, cada participante tiene otro asignado para darle un regalo");
		} else {
			flash.addFlashAttribute("error", "El intercambio no pudo llevarse a cabo, revise que los correos electrónicos son correctos");
		}
		return "redirect:/";
	}
	
	
}
