package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repository.PeriodicityRepository;

@Controller
public class PeriodicityController {
	
	@Autowired
	private PeriodicityRepository repositoryPeriodicity;
	
	
	
	@RequestMapping("/periodicity")
	private String ListPeriodicity(Model model) {
		model.addAttribute("periodicityList", repositoryPeriodicity.findAll());
		return "periodicity";
	}

}
