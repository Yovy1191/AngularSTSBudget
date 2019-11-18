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

import com.example.demo.model.Category;
import com.example.demo.model.Pager;
import com.example.demo.service.ICategoryService;




@Controller
public class CategoryController {

	 private static final int BUTTONS_TO_SHOW = 3;
	 private static final int INITIAL_PAGE = 0;
	 private static final int INITIAL_PAGE_SIZE = 5;
	 private static final int[] PAGE_SIZES = { 5, 10};
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private ICategoryService serviceCategory;
	
	

	public String finalString = null;

	@RequestMapping("/category")
	private String ListCategory(Model model) {
		model.addAttribute("listcategory", serviceCategory.listAll());
		return "category";
	}

	
	@GetMapping("/category")
	 public ModelAndView ShowCategoryPage(@RequestParam("pageSize") Optional<Integer> pageSize,
	            @RequestParam("page") Optional<Integer> page){
	        ModelAndView modelAndView = new ModelAndView("/category");
	        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
	        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
	        Page<Category> listcategory = serviceCategory.findAll(PageRequest.of(evalPage, evalPageSize));
	        Pager pager = new Pager(listcategory.getTotalPages(),listcategory.getNumber(),BUTTONS_TO_SHOW);
	        modelAndView.addObject("listcategory",listcategory);
	        modelAndView.addObject("selectedPageSize", evalPageSize);
	        modelAndView.addObject("pageSizes", PAGE_SIZES);
	        modelAndView.addObject("pager", pager);
	        return modelAndView;
	    }
	
	
	@PostMapping(value = "/addCategory")
	public String SaveCategory(@ModelAttribute @Valid Category newcategory, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			logger.info("Validation errors while submitting form.");
			return "addCategory";
		}
		model.addAttribute("category", newcategory);
		serviceCategory.save(newcategory);
		logger.info("Form submitted successfully.");
		return "redirect:/category";
	}

	@RequestMapping(value = "/editcategory/{idCategory}")
	public String ShowEditCategory(Model model, @PathVariable Long idCategory) {
		model.addAttribute("category", serviceCategory.findOne(idCategory));
		return "editcategory";
	}

	@RequestMapping(value = "editcategory", method = RequestMethod.POST)
	public String saveEditCategory(Model model, Category category, @RequestParam("categoryName") String categoryName) {
		category.setCategoryName(categoryName);
		serviceCategory.save(category);
		return "redirect:/editcategory/" + category.getIdCategory();
	}

	@RequestMapping(value = "/deletecategory/{idCategory}")
	public String deletecategory(Model model, @PathVariable Long idCategory) {
		model.addAttribute("category", serviceCategory.findOne(idCategory));
		serviceCategory.delete(idCategory);
		return "redirect:/category";
	}
	

}
