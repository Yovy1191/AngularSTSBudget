package com.example.demo.Controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Customer;
import com.example.demo.model.TypeIncome;
import com.example.demo.service.ITypeIncomeService;


@Controller
public class TypeIncomeController {

	@Autowired
	private ITypeIncomeService serviceTypeIncome;
	
	private static final Logger logger = LoggerFactory.getLogger(TypeIncomeController.class);

	@RequestMapping("/typeIncome")
	private String ListTypeIncome(Model model) {
		model.addAttribute("listTypeincome", serviceTypeIncome.listAll());
		return "typeIncome";
	}
	
	
	@PostMapping(value = "/addTypeIncome")
	public String SaveTypeIncome(@ModelAttribute @Valid TypeIncome newTypeIncome, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			logger.info("Validation errors while submitting form.");
			return "addTypeIncome";
		}
		model.addAttribute("typeIncome", newTypeIncome);
		serviceTypeIncome.save(newTypeIncome);
		logger.info("Form submitted successfully.");
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
		serviceTypeIncome.deleteById(idincome);
		return "redirect:/typeIncome";
	}

}
