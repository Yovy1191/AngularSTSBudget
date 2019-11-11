package com.example.demo.Controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Customer;
import com.example.demo.model.Pager;
import com.example.demo.model.Property;
import com.example.demo.model.TypeProperty;
import com.example.demo.service.ICustomerService;
import com.example.demo.service.IPropertyService;
import com.example.demo.service.IPropertyTypeService;

@Controller
public class PropertyController {
	
	private static final int BUTTONS_TO_SHOW = 3;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int[] PAGE_SIZES = { 5, 10};
	
	@Autowired
	private IPropertyService serviceProperty;
	
	@Autowired
	private IPropertyTypeService servicePropertyType;
	
	@Autowired
	private ICustomerService serviceCustomer;
	
		
	@RequestMapping("/property")
	private String ListItems(Model model) {
		model.addAttribute("listproperty", serviceProperty.listAll());
		return "property";
	}
	
	@GetMapping("/property")
	 public ModelAndView ShowProertyPage(@RequestParam("pageSize") Optional<Integer> pageSize,
	            @RequestParam("page") Optional<Integer> page){
	        ModelAndView modelAndView = new ModelAndView("/property");
	        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
	        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
	        Page<Property> listproperty = serviceProperty.findAll(PageRequest.of(evalPage, evalPageSize));
	        Pager pager = new Pager(listproperty.getTotalPages(),listproperty.getNumber(),BUTTONS_TO_SHOW);
	        modelAndView.addObject("listproperty",listproperty);
	        modelAndView.addObject("selectedPageSize", evalPageSize);
	        modelAndView.addObject("pageSizes", PAGE_SIZES);
	        modelAndView.addObject("pager", pager);
	        return modelAndView;
	    }
	
	
	
	@RequestMapping(value = "/addproperty", method = RequestMethod.POST)
	public String SaveProperty(Model model, Property property, 	HttpServletRequest request, @RequestParam("idProperty") Long idProperty,
			@RequestParam("value") Double value) {
		property.setIdProperty(idProperty);
		TypeProperty type = new TypeProperty();
		property.setValue(value);
		String propertyTypeid = request.getParameter("idTypeProperty");
		type = servicePropertyType.findOne(Long.parseLong(propertyTypeid));
		property.setPropertyType(type);
		Customer customer = new Customer();
		String customerid = request.getParameter("idCustomer");
		customer = serviceCustomer.findOne(Long.parseLong(customerid));
		property.setCustomer(customer);
		serviceProperty.save(property);
		return "redirect:/addproperty";
	}
	
	@RequestMapping(value = "/editproperty/{idProperty}")
	public String ShowEditProperty(Model model, @PathVariable Long idProperty) {
		model.addAttribute("listpropertytype", servicePropertyType.listAll());
		model.addAttribute("listcustomer", serviceCustomer.listAll());
		model.addAttribute("property",serviceProperty.findOne(idProperty));
		return "editproperty";
	}

	@RequestMapping(value = "editproperty", method = RequestMethod.POST)
	public String saveEditProperty(Model model, HttpServletRequest request, Property property, @RequestParam("value") Double value) {
		TypeProperty type = new TypeProperty();
		String propertyTypeid = request.getParameter("idTypeProperty");
		type = servicePropertyType.findOne(Long.parseLong(propertyTypeid));
		property.setPropertyType(type);
		Customer customer = new Customer();
		String customerid = request.getParameter("idCustomer");
		customer = serviceCustomer.findOne(Long.parseLong(customerid));
		property.setCustomer(customer);
		serviceProperty.save(property);
		return "redirect:/editproperty/" + property.getIdProperty();
	}
	
	@RequestMapping(value = "/deleteproperty/{idProperty}")
	public String deleteProperty(Model model, @PathVariable Long idProperty) {
		model.addAttribute("property", serviceProperty.findOne(idProperty));
		serviceProperty.delete(idProperty);
		return "redirect:/property";
	}
	
}
