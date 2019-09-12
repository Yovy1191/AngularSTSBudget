package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.ServicesOffered;
import com.example.demo.service.IServicesOffered;


@Controller
public class ServicesOfferedController {

	@Autowired
	private IServicesOffered serviceOffered;

	@RequestMapping("/servicesoffered")
	private String ListServices(Model model) {
		model.addAttribute("listservices", serviceOffered.listAll());
		return "servicesoffered";
	}

	@RequestMapping(value = "/addservicesoffered", method = RequestMethod.POST)
	public String SaveServices( @RequestParam("idService") Long idservice,
			@RequestParam("nameService") String nameService) {
		ServicesOffered services = new ServicesOffered();
		services.setIdService(idservice);
		services.setNameService(nameService);
		serviceOffered.save(services);
		return "redirect:/servicesoffered";
	}
	
}
