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
import com.example.demo.model.TypeIncome;
import com.example.demo.service.ITypeIncomeService;


@Controller
public class TypeIncomeController {

	 private static final int BUTTONS_TO_SHOW = 3;
	 private static final int INITIAL_PAGE = 0;
	 private static final int INITIAL_PAGE_SIZE = 5;
	 private static final int[] PAGE_SIZES = { 5, 10};
	
	@Autowired
	private ITypeIncomeService serviceTypeIncome;
	
	private static final Logger logger = LoggerFactory.getLogger(TypeIncomeController.class);

	@RequestMapping("/typeIncome")
	private String ListTypeIncome(Model model) {
		model.addAttribute("listTypeincome", serviceTypeIncome.findAll());
		return "typeIncome";
	}
	
	
	@GetMapping("/typeIncome")
	 public ModelAndView ShowTypeOfIncomePage(@RequestParam("pageSize") Optional<Integer> pageSize,
	            @RequestParam("page") Optional<Integer> page){
	        ModelAndView modelAndView = new ModelAndView("/typeIncome");
	        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
	        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
	        Page<TypeIncome> listTypeincome = serviceTypeIncome.listAll(PageRequest.of(evalPage, evalPageSize));
	        Pager pager = new Pager(listTypeincome.getTotalPages(),listTypeincome.getNumber(),BUTTONS_TO_SHOW);
	        modelAndView.addObject("listTypeincome",listTypeincome);
	        modelAndView.addObject("selectedPageSize", evalPageSize);
	        modelAndView.addObject("pageSizes", PAGE_SIZES);
	        modelAndView.addObject("pager", pager);
	        return modelAndView;
	    }
	
	
	@PostMapping(value = "/addTypeIncome")
	public String SaveTypeIncome(@ModelAttribute @Valid TypeIncome newTypeIncome, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			logger.info("Validation errors while submitting form.");
			return "addTypeIncome";
		}
		model.addAttribute("typeIncome", newTypeIncome);
		serviceTypeIncome.save(newTypeIncome);
		logger.info("Form submitted successfully.");
		return "redirect:/addTypeIncome";
	}
	
	@RequestMapping(value = "/editypeincome/{idincome}")
	public String ShowEditTypeIncome(Model model, @PathVariable Long idincome) {
		model.addAttribute("typeincome", serviceTypeIncome.findOne(idincome));
		return "editypeincome";
	}

	@RequestMapping(value = "editypeincome", method = RequestMethod.POST)
	public String saveEditTypeIncome(Model model, TypeIncome typeincome, @RequestParam("nameIncome") String nameIncome) {
		typeincome.setNameIncome(nameIncome);
		serviceTypeIncome.save(typeincome);
		return "redirect:/editypeincome/" + typeincome.getIdincome();
	}
	
	@RequestMapping(value = "/deletetypeincome/{idincome}")
	public String deleteTypeIncome(Model model, @PathVariable Long idincome) {
		model.addAttribute("typeincome", serviceTypeIncome.findOne(idincome));
		serviceTypeIncome.deleteById(idincome);
		return "redirect:/typeIncome";
	}

}
