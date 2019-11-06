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

import com.example.demo.model.Overview;
import com.example.demo.model.Pager;
import com.example.demo.service.IOverviewService;


@Controller
public class OverviewController {

	 private static final int BUTTONS_TO_SHOW = 3;
	 private static final int INITIAL_PAGE = 0;
	 private static final int INITIAL_PAGE_SIZE = 5;
	 private static final int[] PAGE_SIZES = { 5, 10};
	
	 private static final Logger logger = LoggerFactory.getLogger(OverviewController.class);

	 @Autowired
	 private IOverviewService serviceOverview;

	public String finalString = null;

	@RequestMapping("/overview")
	private String ListOverview(Model model) {
		model.addAttribute("listoverview", serviceOverview.listAll());
		return "overview";
	}

	
	@GetMapping("/overview")
	 public ModelAndView ShowOverviewPage(@RequestParam("pageSize") Optional<Integer> pageSize,
	            @RequestParam("page") Optional<Integer> page){
	        ModelAndView modelAndView = new ModelAndView("/overview");
	        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
	        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
	        Page<Overview> listoverview = serviceOverview.findAll(PageRequest.of(evalPage, evalPageSize));
	        Pager pager = new Pager(listoverview.getTotalPages(),listoverview.getNumber(),BUTTONS_TO_SHOW);
	        modelAndView.addObject("listoverview",listoverview);
	        modelAndView.addObject("selectedPageSize", evalPageSize);
	        modelAndView.addObject("pageSizes", PAGE_SIZES);
	        modelAndView.addObject("pager", pager);
	        return modelAndView;
	    }
	
	
	@PostMapping(value = "/addOverview")
	public String SaveCategory(@ModelAttribute @Valid Overview newoverview, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			logger.info("Validation errors while submitting form.");
			return "addOverview";
		}
		model.addAttribute("overview", newoverview);
		serviceOverview.save(newoverview);
		logger.info("Form submitted successfully.");
		return "redirect:/overview";
	}

	@RequestMapping(value = "/editoverview/{idOverview}")
	public String ShowEditCategory(Model model, @PathVariable Long idOverview) {
		model.addAttribute("overview", serviceOverview.findOne(idOverview));
		return "editoverview";
	}

	@RequestMapping(value = "editoverview", method = RequestMethod.POST)
	public String saveEditOverview(Model model, Overview overview, @RequestParam("nameOverview") String nameOverview,
			@RequestParam("valueOverview") Double valueOverview) {
		overview.setNameOverview(nameOverview);
		overview.setValueOverview(valueOverview);
		serviceOverview.save(overview);
		return "redirect:/editoverview/" + overview.getIdOverview();
	}

	@RequestMapping(value = "/deleteoverview/{idOverview}")
	public String deletecategory(Model model, @PathVariable Long idOverview) {
		model.addAttribute("overview", serviceOverview.findOne(idOverview));
		serviceOverview.delete(idOverview);
		return "redirect:/overview";
	}

}
