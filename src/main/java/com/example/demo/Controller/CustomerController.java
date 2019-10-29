package com.example.demo.Controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Customer;
import com.example.demo.model.Pager;
import com.example.demo.service.ICustomerService;

@Controller
public class CustomerController {

	 private static final int BUTTONS_TO_SHOW = 3;
	 private static final int INITIAL_PAGE = 0;
	 private static final int INITIAL_PAGE_SIZE = 5;
	 private static final int[] PAGE_SIZES = { 5, 10};
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private ICustomerService serviceCustomer;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	public String finalString = null;

	@RequestMapping("/customer")
	private String ListCustomer(Model model) {
		model.addAttribute("listcustomer", serviceCustomer.listAll());
		return "customer";
	}

	// @PostMapping(value = "/addCustomer", method = RequestMethod.POST)

	@GetMapping("/customer")
	 public ModelAndView ShowSupplierPage(@RequestParam("pageSize") Optional<Integer> pageSize,
	            @RequestParam("page") Optional<Integer> page){
	        ModelAndView modelAndView = new ModelAndView("/customer");
	        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
	        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
	        Page<Customer> listcustomer = serviceCustomer.findAll(PageRequest.of(evalPage, evalPageSize));
	        Pager pager = new Pager(listcustomer.getTotalPages(),listcustomer.getNumber(),BUTTONS_TO_SHOW);
	        modelAndView.addObject("listcustomer",listcustomer);
	        modelAndView.addObject("selectedPageSize", evalPageSize);
	        modelAndView.addObject("pageSizes", PAGE_SIZES);
	        modelAndView.addObject("pager", pager);
	        return modelAndView;
	    }
	
	
	@PostMapping(value = "/addCustomer")
	public String SaveCustomer(@ModelAttribute @Valid Customer newcustomer, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			logger.info("Validation errors while submitting form.");
			return "addCustomer";
		}
		model.addAttribute("customer", newcustomer);
		serviceCustomer.save(newcustomer);
		logger.info("Form submitted successfully.");
		return "redirect:/customer";
	}

	@RequestMapping(value = "/editcustomer/{idCustomer}")
	public String ShowEditCustomer(Model model, @PathVariable Long idCustomer) {
		model.addAttribute("customer", serviceCustomer.findOne(idCustomer));
		return "editcustomer";
	}

	@RequestMapping(value = "editcustomer", method = RequestMethod.POST)
	public String saveEditCustomer(Model model, Customer customer, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName) {
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		serviceCustomer.save(customer);
		return "redirect:/editcustomer/" + customer.getIdCustomer();
	}

	@RequestMapping(value = "/deletecustomer/{idCustomer}")
	public String deleteCustomer(Model model, @PathVariable Long idCustomer) {
		model.addAttribute("customer", serviceCustomer.findOne(idCustomer));
		serviceCustomer.delete(idCustomer);
		return "redirect:/customer";
	}

}
