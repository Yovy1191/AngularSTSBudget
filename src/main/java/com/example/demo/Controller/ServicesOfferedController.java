package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value = "/editservice/{idService}")
	public String ShowEditService(Model model, @PathVariable Long idService) {
		model.addAttribute("service", serviceOffered.findOne(idService));
		return "editservice";
	}

	@RequestMapping(value = "editservice", method = RequestMethod.POST)
	public String saveEditSupplier(Model model, ServicesOffered service, @RequestParam("nameService") String nameService) {
		service.setNameService(nameService);
		serviceOffered.save(service);
		return "redirect:/editservice/" + service.getIdService();
	}

	@RequestMapping(value = "/deleteservice/{idService}")
	public String deleteCustomer(Model model, @PathVariable Long idService) {
		model.addAttribute("service", serviceOffered.findOne(idService));
		serviceOffered.delete(idService);
		return "redirect:/servicesoffered";
	}
	
	
}
