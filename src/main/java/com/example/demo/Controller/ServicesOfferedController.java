package com.example.demo.Controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Pager;

import com.example.demo.model.ServicesOffered;
import com.example.demo.service.IServicesOffered;


@Controller
public class ServicesOfferedController {

	private static final Logger logger = LoggerFactory.getLogger(ServicesOfferedController.class);

	
	 private static final int BUTTONS_TO_SHOW = 3;
	 private static final int INITIAL_PAGE = 0;
	 private static final int INITIAL_PAGE_SIZE = 5;
	 private static final int[] PAGE_SIZES = { 5, 10};
	
	
	
	@Autowired
	private IServicesOffered serviceOffered;


	@GetMapping("/servicesoffered")
	 public ModelAndView ShowServicesPage(@RequestParam("pageSize") Optional<Integer> pageSize,
	            @RequestParam("page") Optional<Integer> page){
	        ModelAndView modelAndView = new ModelAndView("servicesoffered");
	        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
	        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
	        Page<ServicesOffered> listservices = serviceOffered.listAll(PageRequest.of(evalPage, evalPageSize));
	        Pager pager = new Pager(listservices.getTotalPages(),listservices.getNumber(),BUTTONS_TO_SHOW);
	        modelAndView.addObject("listservices",listservices);
	        modelAndView.addObject("selectedPageSize", evalPageSize);
	        modelAndView.addObject("pageSizes", PAGE_SIZES);
	        modelAndView.addObject("pager", pager);
	        return modelAndView;
	    }
	
	
	@PostMapping(value = "/addservicesoffered")
	public String SaveServices(@ModelAttribute @Valid ServicesOffered service,BindingResult bindingResult, Model model) {
		System.out.println("test1 ");
	//	model.addAttribute("service", service);
		if (bindingResult.hasErrors()) {
			System.out.println("test2 ");
			logger.info("Validation errors while submitting form.");
			return "addservicesoffered";
		}
		
		serviceOffered.save(service);
		logger.info("Form submitted successfully.");
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
