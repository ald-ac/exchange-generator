package com.ald.exchangegenerator.app.controllers;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ald.exchangegenerator.app.models.domain.Contestant;
import com.ald.exchangegenerator.app.models.domain.ContestantList;

@Controller
@SessionAttributes({"contestants"})
public class HomeController {
	
	@ModelAttribute("contestants")
	public ContestantList contestants() {
		return new ContestantList();
	}
	
	@GetMapping("/")
	public String home(@ModelAttribute(name = "contestants") ContestantList contestants) {
		contestants.clear();
		return "home";
	}
	
	@GetMapping("/generator")
	public String generator(Model model) {
//		Contestant contestant = new Contestant();
//		contestant.setName("Aldair");
//		contestant.setMail("ald@gmail.com");
//		contestant.setId("27mayo");
//		contestants.add(contestant);
		model.addAttribute("contestant", new Contestant());
		return "generator";
	}
	
	@PostMapping("/addContestant")
	public String addContestant(@ModelAttribute Contestant contestant, @ModelAttribute(name = "contestants") ContestantList contestants) {
		contestant.setId(LocalDateTime.now().toString());
		contestants.add(contestant);
		return "redirect:/generator";
	}
	
	@GetMapping("/removeContestant/{id}")
	public String removeContestant(@PathVariable(name = "id") String id, @ModelAttribute(name = "contestants") ContestantList contestants) {
		contestants.removeIf(item -> item.getId().equals(id));
		return "redirect:/generator";
	}
}
