package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Customer;
import com.example.demo.model.Income;
import com.example.demo.service.ICustomerService;
import com.example.demo.service.IIncomeService;

@Controller
public class CustomerController {

	@Autowired
	private ICustomerService service;
	
	@Autowired
	private IIncomeService serviceIncome;


	@RequestMapping("/customer")
	private String ListCustomer(Model model) {
		model.addAttribute("listcustomer", service.listAll());
		return "customer";
	}

	 @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
	 public String SaveCustomer(Model model, Customer customer,
	 @RequestParam("idCustomer") Integer idCustomer,
	 @RequestParam("firstName") String firstName, @RequestParam("lastName") String
	 lastName,  @RequestParam("income") Income income, @RequestParam("incomeidincome") Long incomeidincome ) {
	 customer.setFirstName(firstName);
	 customer.setLastName(lastName);
	 income = serviceIncome.findOne(incomeidincome);
	 service.save(customer);
	 return "redirect:/addCustomer";
	 }
	

	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST, params = "action=SearchIncome")
	public String ListIncome(Model model,  @RequestParam(value = "action", required = true) String action) {
		model.addAttribute("listincome",  serviceIncome.listAll());
		return "/addCustomer";
	}

	@RequestMapping(value = "/editcustomer/{idCustomer}")
	public String ShowEditCustomer(Model model, @PathVariable Long idCustomer) {
		model.addAttribute("customer", service.findOne(idCustomer));
		return "editcustomer";
	}

	@RequestMapping(value = "editcustomer", method = RequestMethod.POST)
	public String saveEditCustomer(Model model, Customer customer, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("incomeidincome") Long incomeidincome) {
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		Income income1 = new Income(); 
		 income1 = serviceIncome.findOne(incomeidincome);
		 customer.setIncome(income1);
		service.save(customer);
		return "redirect:/editcustomer/" + customer.getIdCustomer();
	}
	
	@RequestMapping(value = "/deletecustomer/{idCustomer}")
	public String deleteCustomer(Model model, @PathVariable Long idCustomer) {
		model.addAttribute("customer", service.findOne(idCustomer));
		service.delete(idCustomer);
		return "redirect:/customer";
	}

}
