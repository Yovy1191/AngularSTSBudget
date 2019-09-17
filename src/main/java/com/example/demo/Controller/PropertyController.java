package com.example.demo.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Property;
import com.example.demo.model.TypeProperty;
import com.example.demo.service.IPropertyService;
import com.example.demo.service.IPropertyTypeService;

@Controller
public class PropertyController {
	

	@Autowired
	private IPropertyService serviceProperty;
	
	@Autowired
	private IPropertyTypeService servicePropertyType;
	
		
	@RequestMapping("/property")
	private String ListItems(Model model) {
		model.addAttribute("listproperty", serviceProperty.listAll());
		return "property";
	}
	
	@RequestMapping(value = "/addproperty", method = RequestMethod.POST)
	public String SaveProperty(Model model, Property property, 	HttpServletRequest request, @RequestParam("idProperty") Long idProperty,
			@RequestParam("value") Long value) {
		property.setIdProperty(idProperty);
		TypeProperty type = new TypeProperty();
		property.setValue(value);
		String propertyTypeid = request.getParameter("idTypeProperty");
		type = servicePropertyType.findOne(Long.parseLong(propertyTypeid));
		property.setPropertyType(type);
		serviceProperty.save(property);
		return "redirect:/addproperty";
	}
	
	@RequestMapping(value = "/deleteproperty/{idProperty}")
	public String deleteProperty(Model model, @PathVariable Long idProperty) {
		model.addAttribute("property", serviceProperty.findOne(idProperty));
		serviceProperty.delete(idProperty);
		return "redirect:/property";
	}





	
}
