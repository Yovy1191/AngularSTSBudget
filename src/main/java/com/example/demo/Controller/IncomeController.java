package com.example.demo.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Customer;
import com.example.demo.model.Income;
import com.example.demo.model.TypeIncome;
import com.example.demo.service.ICustomerService;
import com.example.demo.service.IIncomeService;
import com.example.demo.service.ITypeIncomeService;

@Controller
public class IncomeController {
	

	@Autowired
	private IIncomeService serviceIncome;
	
	@Autowired
	private ITypeIncomeService serviceTypeIncome;
	
	@Autowired
	private ICustomerService service;

		
	@RequestMapping("/income")
	private String ListIncome(Model model) {
		model.addAttribute("listIncome", serviceIncome.listAll());
		return "income";
	}
	
	@RequestMapping(value = "/addincome", method = RequestMethod.POST)
	public String SaveIncome(Model model, Income income, HttpServletRequest request,
			@RequestParam("idIncome") Long idIncome, @RequestParam("date") String date, @RequestParam("value") Double value
			) {
	income.setIdIncome(idIncome);	
	income.setDate(date);
	income.setValue(value);
	String incometypeid = request.getParameter("idTypeIncome");
	TypeIncome typeincome = new TypeIncome();
	typeincome = serviceTypeIncome.findOne(Long.parseLong(incometypeid));
	income.setTypeIncome(typeincome);	
	String idCustomer = request.getParameter("idCustomer");
	Customer customer = new Customer();
	customer = service.findOne(Long.parseLong(idCustomer));
  	income.setCustomer(customer);
  	serviceIncome.save(income);
	return "redirect:/addincome";
	}

	
	@RequestMapping(value = "/editincome/{idIncome}")
	public String ShowEditIncome(Model model, @PathVariable Long idIncome) {
		model.addAttribute("listTypeincome", serviceTypeIncome.listAll());
		model.addAttribute("listcustomer", service.listAll());
		model.addAttribute("income", serviceIncome.findOne(idIncome));
		return "editincome";
	}
	
	@RequestMapping(value = "editincome", method = RequestMethod.POST)
	public String saveEditIncome(Model model, Income income, @RequestParam("date") String date, HttpServletRequest request,
			@RequestParam("value") Double value) {
		income.setDate(date);
		String incometypeid = request.getParameter("idTypeIncome");
		TypeIncome typeincome = new TypeIncome();
		typeincome = serviceTypeIncome.findOne(Long.parseLong(incometypeid));
		income.setTypeIncome(typeincome);	
		String idCustomer = request.getParameter("idCustomer");
		Customer customer = new Customer();
		customer = service.findOne(Long.parseLong(idCustomer));
	  	income.setCustomer(customer);
		serviceIncome.save(income);
		return "redirect:/editincome/" + income.getIdIncome();
	}
	

	@RequestMapping(value = "/deleteincome/{idIncome}")
	public String deleteIncome(Model model, @PathVariable Long idIncome) {
		model.addAttribute("income", serviceIncome.findOne(idIncome));
		serviceIncome.delete(idIncome);
		return "redirect:/income";
	}


}
