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

import com.example.demo.model.Description;
import com.example.demo.model.Pager;
import com.example.demo.service.IDescriptionService;


@Controller
public class DescriptionController {

	 private static final int BUTTONS_TO_SHOW = 3;
	 private static final int INITIAL_PAGE = 0;
	 private static final int INITIAL_PAGE_SIZE = 5;
	 private static final int[] PAGE_SIZES = { 5, 10};
	
	private static final Logger logger = LoggerFactory.getLogger(DescriptionController.class);

	@Autowired
	private IDescriptionService serviceDescription;
	

	@RequestMapping("/description")
	private String ListDescription(Model model) {
		model.addAttribute("listdescription", serviceDescription.listAll());
		return "description";
	}

	
	@GetMapping("/description")
	 public ModelAndView ShowDescriptionPage(@RequestParam("pageSize") Optional<Integer> pageSize,
	            @RequestParam("page") Optional<Integer> page){
	        ModelAndView modelAndView = new ModelAndView("/description");
	        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
	        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
	        Page<Description> listdescription = serviceDescription.findAll(PageRequest.of(evalPage, evalPageSize));
	        Pager pager = new Pager(listdescription.getTotalPages(),listdescription.getNumber(),BUTTONS_TO_SHOW);
	        modelAndView.addObject("listdescription",listdescription);
	        modelAndView.addObject("selectedPageSize", evalPageSize);
	        modelAndView.addObject("pageSizes", PAGE_SIZES);
	        modelAndView.addObject("pager", pager);
	        return modelAndView;
	    }
	
	
	@PostMapping(value = "/addDescription")
	public String SaveDescription(@ModelAttribute @Valid Description newdescription, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			logger.info("Validation errors while submitting form.");
			return "addDescription";
		}
		model.addAttribute("description", newdescription);
		serviceDescription.save(newdescription);
		logger.info("Form submitted successfully.");
		return "redirect:/description";
	}

	@RequestMapping(value = "/editdescription/{descriptionId}")
	public String ShowEditCategory(Model model, @PathVariable Long descriptionId) {
		model.addAttribute("description", serviceDescription.findOne(descriptionId));
		return "editdescription";
	}

	@RequestMapping(value = "editdescription", method = RequestMethod.POST)
	public String saveEditDescription(Model model, Description description, @RequestParam("descriptionName") String descriptionName) {
		description.setDescriptionName(descriptionName);
		serviceDescription.save(description);
		return "redirect:/editdescription/" + description.getDescriptionId();
	}

	@RequestMapping(value = "/deletedescription/{descriptionId}")
	public String deleteDescription(Model model, @PathVariable Long descriptionId) {
		model.addAttribute("description", serviceDescription.findOne(descriptionId));
		serviceDescription.delete(descriptionId);
		return "redirect:/description";
	}
	

}
