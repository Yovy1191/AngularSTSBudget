package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.TypeIncome;
import com.example.demo.service.ITypeIncomeService;


@Controller
public class TypeIncomeController {

	@Autowired
	private ITypeIncomeService serviceTypeIncome;

	@RequestMapping("/typeIncome")
	private String ListTypeIncome(Model model) {
		model.addAttribute("listTypeincome", serviceTypeIncome.listAll());
		return "typeIncome";
	}
	
	
	@RequestMapping(value = "/addTypeIncome", method = RequestMethod.POST)
	public String SaveIncome( @RequestParam("idincome") Long idincome,
			@RequestParam("nameIncome") String nameIncome) {
		TypeIncome income = new TypeIncome();
		income.setNameIncome(nameIncome);
		serviceTypeIncome.save(income);
		return "redirect:/addTypeIncome";
	}
	
	@RequestMapping(value = "/editypeincome/{idincome}")
	public String ShowEditTypeIncome(Model model, @PathVariable Long idincome) {
		model.addAttribute("typeincome", serviceTypeIncome.findOne(idincome));
		return "editypeincome";
	}

	@RequestMapping(value = "editypeincome", method = RequestMethod.POST)
	public String saveEditTypeIncome(Model model, TypeIncome typeincome, @RequestParam("nameIncome") String nameIncome) {
		typeincome.setNameIncome(nameIncome);
		serviceTypeIncome.save(typeincome);
		return "redirect:/editypeincome/" + typeincome.getIdincome();
	}
	
	@RequestMapping(value = "/deletetypeincome/{idincome}")
	public String deleteTypeIncome(Model model, @PathVariable Long idincome) {
		model.addAttribute("typeincome", serviceTypeIncome.findOne(idincome));
		serviceTypeIncome.delete(idincome);
		return "redirect:/income";
	}

}
