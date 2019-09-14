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
import com.example.demo.service.ICustomerService;



@Controller
public class CustomerController {

	@Autowired
	private ICustomerService service;




	@RequestMapping("/customer")
	private String ListCustomer(Model model) {
		model.addAttribute("listcustomer", service.listAll());
		return "customer";
	}

	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
	public String SaveCustomer(Model model, Customer customer, HttpServletRequest request,
			@RequestParam("idCustomer") Integer idCustomer, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) {
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		service.save(customer);
		return "redirect:/addCustomer";
	}

//	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST, params = "action=SearchIncome")
//	public String ListIncome(Model model, @RequestParam(value = "action", required = true) String action) {
//		model.addAttribute("listincome", serviceIncome.listAll());
//		return "/addCustomer";
//	}

	@RequestMapping(value = "/editcustomer/{idCustomer}")
	public String ShowEditCustomer(Model model, @PathVariable Long idCustomer) {
		model.addAttribute("customer", service.findOne(idCustomer));
		return "editcustomer";
	}

	@RequestMapping(value = "editcustomer", method = RequestMethod.POST)
	public String saveEditCustomer(Model model, Customer customer, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) {
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
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
