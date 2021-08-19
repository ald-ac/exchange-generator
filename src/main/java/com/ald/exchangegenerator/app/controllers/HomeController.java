package com.ald.exchangegenerator.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ald.exchangegenerator.app.models.domain.ContestantList;

@Controller
@SessionAttributes({"contestants"})
public class HomeController {
	
	@ModelAttribute("contestants")
	public ContestantList contestants() {
		return new ContestantList();
	}
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/generator")
	public String generator() {
		return "generator";
	}
}
