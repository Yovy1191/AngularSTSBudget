package com.example.demo.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.TypeProperty;
import com.example.demo.service.IPropertyTypeService;

@Controller
public class PropertyTypeController {
	

	@Autowired
	private IPropertyTypeService servicePropertyType;
	
		
	@RequestMapping("/typeproperty")
	private String ListItems(Model model) {
		model.addAttribute("listpropertytype", servicePropertyType.listAll());
		return "typeproperty";
	}
	
	
	@RequestMapping(value = "/addtypeproperty", method = RequestMethod.POST)
	public String SavePropertyType(Model model, TypeProperty propertytype, HttpServletRequest request,
			@RequestParam("idTypeProperty") Long idTypeProperty, @RequestParam("nameProperty") String nameProperty) {
		propertytype.setIdTypeProperty(idTypeProperty);
		propertytype.setNameProperty(nameProperty);
	servicePropertyType.save(propertytype);
		
		return "redirect:/addtypeproperty";
	}


	
}
