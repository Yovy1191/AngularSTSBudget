package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Supplier;
import com.example.demo.service.ISupplierService;

@Controller
public class SupplierController {

	@Autowired
	private ISupplierService serviceSupplier;

	@RequestMapping("/supplier")
	private String ListSupplier(Model model) {
		model.addAttribute("listsupplier", serviceSupplier.listAll());
		return "supplier";
	}

	
	@RequestMapping(value = "/addsupplier", method = RequestMethod.POST)
	public String SaveSupplier( @RequestParam("idsupplier") Long idsupplier,
			@RequestParam("nameSupplier") String nameSupplier) {
		Supplier customerSupplier = new Supplier();
		customerSupplier.setIdsupplier(idsupplier);
		customerSupplier.setNameSupplier(nameSupplier);
		serviceSupplier.save(customerSupplier);
		return "redirect:/addsupplier";
	}

}