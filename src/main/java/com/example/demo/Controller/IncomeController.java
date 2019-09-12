package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Income;
import com.example.demo.service.IIncomeService;

@Controller
public class IncomeController {

	@Autowired
	private IIncomeService serviceIncome;

	@RequestMapping("/income")
	private String ListIncome(Model model) {
		model.addAttribute("listincome", serviceIncome.listAll());
		return "income";
	}
	
	
	@RequestMapping(value = "/addincome", method = RequestMethod.POST)
	public String SaveIncome( @RequestParam("idincome") Long idincome,
			@RequestParam("nameIncome") String nameIncome) {
		Income income = new Income();
		
		income.setNameIncome(nameIncome);
		serviceIncome.save(income);
		return "redirect:/addincome";
	}

}
