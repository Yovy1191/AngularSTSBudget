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

import com.example.demo.model.PagerModel;
import com.example.demo.model.Supplier;
import com.example.demo.service.ISupplierService;

@Controller
public class SupplierController {

	 private static final int BUTTONS_TO_SHOW = 3;
	 private static final int INITIAL_PAGE = 0;
	 private static final int INITIAL_PAGE_SIZE = 5;
	 private static final int[] PAGE_SIZES = { 5, 15};
	
	
	@Autowired
	private ISupplierService serviceSupplier;
	
	
	private static final Logger logger = LoggerFactory.getLogger(SupplierController.class);

	@GetMapping("/supplier")
	 public ModelAndView SupplierPage(@RequestParam("pageSize") Optional<Integer> pageSize,
	            @RequestParam("page") Optional<Integer> page){
	        ModelAndView modelAndView = new ModelAndView("supplier");
	        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
	        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
	        Page<Supplier> listsupplier = serviceSupplier.listAll(PageRequest.of(evalPage, evalPageSize));
	        PagerModel pager = new PagerModel(listsupplier.getTotalPages(),listsupplier.getNumber(),BUTTONS_TO_SHOW);
	        modelAndView.addObject("listsupplier",listsupplier);
	        modelAndView.addObject("selectedPageSize", evalPageSize);
	        modelAndView.addObject("pageSizes", PAGE_SIZES);
	        modelAndView.addObject("pager", pager);
	        return modelAndView;
	    }
	
	@PostMapping(value = "/addsupplier")
	public String SaveSupplier(@ModelAttribute @Valid Supplier newsupplier, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			logger.info("Validation errors while submitting form.");
			return "addsupplier";
		}
		model.addAttribute("supplier", newsupplier);
		serviceSupplier.save(newsupplier);
		logger.info("Form submitted successfully.");
		return "redirect:/supplier";
	}
	
	@RequestMapping(value = "/editsupplier/{idsupplier}")
	public String ShowEditSupplier(Model model, @PathVariable Long idsupplier) {
		model.addAttribute("supplier", serviceSupplier.findOne(idsupplier));
		return "editsupplier";
	}

	@RequestMapping(value = "editsupplier", method = RequestMethod.POST)
	public String saveEditSupplier(Model model, Supplier supplier, @RequestParam("nameSupplier") String nameSupplier) {
		supplier.setNameSupplier(nameSupplier);
		serviceSupplier.save(supplier);
		return "redirect:/editsupplier/" + supplier.getIdsupplier();
	}

	@RequestMapping(value = "/deletesupplier/{idsupplier}")
	public String deleteCustomer(Model model, @PathVariable Long idsupplier) {
		model.addAttribute("supplier", serviceSupplier.findOne(idsupplier));
		serviceSupplier.delete(idsupplier);
		return "redirect:/supplier";
	}
	
	

}
