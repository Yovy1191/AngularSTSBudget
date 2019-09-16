package com.example.demo.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Customer;
import com.example.demo.model.Property;
import com.example.demo.service.IPropertyService;

@Controller
public class PropertyController {
	

	@Autowired
	private IPropertyService serviceProperty;
	
		
	@RequestMapping("/property")
	private String ListItems(Model model) {
		model.addAttribute("listproperty", serviceProperty.listAll());
		return "property";
	}
	
	
	@RequestMapping(value = "/addproperty", method = RequestMethod.POST)
	public String SaveProperty(Model model, Property property, HttpServletRequest request,
			@RequestParam("idProperty") Long idProperty, @RequestParam("nameProperty") String nameProperty,
			@RequestParam("value") Long value) {
		property.setNameProperty(nameProperty);
		property.setValue(value);
		serviceProperty.save(property);
		return "redirect:/addproperty";
	}


	
}
