package com.example.demo.Controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.TypeProperty;
import com.example.demo.service.IPropertyTypeService;

@Controller
public class PropertyTypeController {
	
	private static final Logger logger = LoggerFactory.getLogger(PropertyTypeController.class);

	@Autowired
	private IPropertyTypeService servicePropertyType;
	
		
	@RequestMapping("/typeproperty")
	private String ListItems(Model model) {
		model.addAttribute("listpropertytype", servicePropertyType.listAll());
		return "typeproperty";
	}
	
	@PostMapping(value = "/addtypeproperty")
	public String SaveTypeProperty(@ModelAttribute @Valid TypeProperty typeproperty, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			logger.info("Validation errors while submitting form.");
			return "addtypeproperty";
		}
		model.addAttribute("typeproperty", typeproperty);
		servicePropertyType.save(typeproperty);
		logger.info("Form submitted successfully.");
		return "redirect:/addtypeproperty";
	}
	
//	@RequestMapping(value = "/addtypeproperty", method = RequestMethod.POST)
//	public String SavePropertyType(Model model, TypeProperty propertytype, HttpServletRequest request,
//			@RequestParam("idTypeProperty") Long idTypeProperty, @RequestParam("nameProperty") String nameProperty) {
//		propertytype.setIdTypeProperty(idTypeProperty);
//		propertytype.setNameProperty(nameProperty);
//	servicePropertyType.save(propertytype);
//		
//		return "redirect:/addtypeproperty";
//	}

	@RequestMapping(value = "/editypeproperty/{idTypeProperty}")
	public String ShowEditTypeProperty(Model model, @PathVariable Long idtypeproperty) {
		model.addAttribute("typeproperty",servicePropertyType.findOne(idtypeproperty));
		return "editypeproperty";
	}

	@RequestMapping(value = "editypeproperty", method = RequestMethod.POST)
	public String saveEditTypeProperty(Model model, TypeProperty typeproperty, @RequestParam("nameProperty") String nameProperty) {
		typeproperty.setNameProperty(nameProperty);
		servicePropertyType.save(typeproperty);
		return "redirect:/editypeproperty/" + typeproperty.getIdtypeproperty();
	}
	
	@RequestMapping(value = "/deletetypeproperty/{idtypeproperty}")
	public String deleteTypeProperty(Model model, @PathVariable Long idtypeproperty) {
		model.addAttribute("typeproperty", servicePropertyType.findOne(idtypeproperty));
		servicePropertyType.delete(idtypeproperty);
		return "redirect:/property";
	}
	
	
}
