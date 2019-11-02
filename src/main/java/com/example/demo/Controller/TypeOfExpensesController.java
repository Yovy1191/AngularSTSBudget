package com.example.demo.Controller;


import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Pager;
import com.example.demo.model.Supplier;
import com.example.demo.model.TypesOfExpenses;
import com.example.demo.service.ITypeOfExpensesService;

@Controller
public class TypeOfExpensesController {
	
	 private static final Logger logger = LoggerFactory.getLogger(TypeOfExpensesController.class);
	 
	 private static final int BUTTONS_TO_SHOW = 3;
	 private static final int INITIAL_PAGE = 0;
	 private static final int INITIAL_PAGE_SIZE = 5;
	 private static final int[] PAGE_SIZES = { 5, 10};
	 
	
	@Autowired
	private ITypeOfExpensesService serviceTypeOfExpenses;
	
		
	@RequestMapping("/typeExpenses")
	private String ListTypesOfExpenses(Model model) {
		model.addAttribute("listOfExpenses", serviceTypeOfExpenses.listAll());
		return "typeExpenses";
	}
	
	@GetMapping("/typeExpenses")
	 public ModelAndView ShowTypeOfExpensesPage(@RequestParam("pageSize") Optional<Integer> pageSize,
	            @RequestParam("page") Optional<Integer> page){
	        ModelAndView modelAndView = new ModelAndView("/typeExpenses");
	        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
	        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
	        Page<TypesOfExpenses> listOfExpenses = serviceTypeOfExpenses.findAll(PageRequest.of(evalPage, evalPageSize));
	        Pager pager = new Pager(listOfExpenses.getTotalPages(),listOfExpenses.getNumber(),BUTTONS_TO_SHOW);
	        modelAndView.addObject("listOfExpenses",listOfExpenses);
	        modelAndView.addObject("selectedPageSize", evalPageSize);
	        modelAndView.addObject("pageSizes", PAGE_SIZES);
	        modelAndView.addObject("pager", pager);
	        return modelAndView;
	    }
	
	
	@PostMapping(value = "/addtypexpenses")
	public String SaveTypeOfExpenses(@ModelAttribute @Valid TypesOfExpenses newTypeOfexpense, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			logger.info("Validation errors while submitting form.");
			return "addtypexpenses";
		}
		model.addAttribute("typexpenses", newTypeOfexpense);
		serviceTypeOfExpenses.save(newTypeOfexpense);
		logger.info("Form submitted successfully.");
		return "redirect:/typeExpenses";
	}
	
	@RequestMapping(value = "/editypeofexpenses/{idExpense}")
	public String ShowEditService(Model model, @PathVariable Long idExpense) {
		model.addAttribute("typeOfexpenses", serviceTypeOfExpenses.findOne(idExpense));
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
