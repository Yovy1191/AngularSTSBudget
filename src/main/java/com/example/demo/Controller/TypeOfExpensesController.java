package com.example.demo.Controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.TypesOfExpenses;
import com.example.demo.service.ITypeOfExpensesService;

@Controller
public class TypeOfExpensesController {
	
	@Autowired
	private ITypeOfExpensesService serviceTypeOfExpenses;
	
		
	@RequestMapping("/typeExpenses")
	private String ListTypesOfExpenses(Model model) {
		model.addAttribute("listOfExpenses", serviceTypeOfExpenses.listAll());
		return "typeExpenses";
	}
	
	
	@RequestMapping(value = "/addtypexpenses", method = RequestMethod.POST)
	public String SaveTypeOfExpenses(Model model, TypesOfExpenses expense, HttpServletRequest request,
			@RequestParam("idExpense") Long idExpense, @RequestParam("nameTypeExpense") String nameTypeExpense) {
		expense.setIdExpense(idExpense);
		expense.setNameTypeExpense(nameTypeExpense);
		serviceTypeOfExpenses.save(expense);
		return "redirect:/addtypexpenses";
	}
	
	@RequestMapping(value = "/editypeofexpenses/{idExpense}")
	public String ShowEditService(Model model, @PathVariable Long idExpense) {
		model.addAttribute("expenses", serviceTypeOfExpenses.findOne(idExpense));
		return "editypeofexpenses";
	}

	@RequestMapping(value = "editypeofexpenses", method = RequestMethod.POST)
	public String saveEditSupplier(Model model, TypesOfExpenses expense, @RequestParam("nameTypeExpense") String nameTypeExpense) {
		expense.setNameTypeExpense(nameTypeExpense);
		serviceTypeOfExpenses.save(expense);
		return "redirect:/editypeofexpenses/" + expense.getIdExpense();
	}

	@RequestMapping(value = "/deletetypeofexpenses/{idService}")
	public String deleteCustomer(Model model, @PathVariable Long idExpense) {
		model.addAttribute("expenses", serviceTypeOfExpenses.findOne(idExpense));
		serviceTypeOfExpenses.deleteById(idExpense);
		return "redirect:/typeExpenses";
	}
}
