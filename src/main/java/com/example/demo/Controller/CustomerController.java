package com.example.demo.Controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
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

import com.example.demo.model.Customer;
import com.example.demo.service.ICustomerService;

@Controller
public class CustomerController {

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
	
	
	//@PostMapping(value = "/addCustomer", method = RequestMethod.POST)

	@PostMapping(value = "/addCustomer")
	public String SaveCustomer(@ModelAttribute @Valid Customer newcustomer, BindingResult bindingResult, Model model
			) {
		System.out.println("helllpoooooooo");
		
		 if (bindingResult.hasErrors()) {
			    logger.info("Validation errors while submitting form.");
	            return "addCustomer";
	        } 
		 
	      		model.addAttribute("customer", newcustomer);
	        		
//	        		 if (newcustomer != null) {
//	                     try {
//	                         // check for comments and if not present set to 'none'
//	                         String lastname = checkNullString(newcustomer.getLastName());
//	                         if (lastname != "") {
//	                             System.out.println("nothing changes");
//	                         } else {
//	                        	 newcustomer.setLastName(lastname);;
//	                         }
//	                     } catch (Exception e) {
//	      
//	                         System.out.println(e);
//	      
//	                     }
//	                    
//	                 }
	      
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
	
	
	 public String checkNullString(String str){
	        String endString = null;
	        if(str == null || str.isEmpty()){
	            System.out.println("yes it is empty");
	            str = null;
	            Optional<String> opt = Optional.ofNullable(str);
	            endString = opt.orElse("None");
	            System.out.println("endString : " + endString);
	        }
	        else{
	            ; //do nothing
	        }
	         
	         
	        return endString;
	         
	    }

}
