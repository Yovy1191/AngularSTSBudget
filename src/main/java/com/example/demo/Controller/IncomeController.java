package com.example.demo.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Customer;
import com.example.demo.model.Income;
import com.example.demo.model.Pager;
import com.example.demo.model.TypeIncome;
import com.example.demo.service.ICustomerService;
import com.example.demo.service.IIncomeService;
import com.example.demo.service.ITypeIncomeService;

@Controller
public class IncomeController {
	
	 private static final int BUTTONS_TO_SHOW = 3;
	 private static final int INITIAL_PAGE = 0;
	 private static final int INITIAL_PAGE_SIZE = 5;
	 private static final int[] PAGE_SIZES = { 5, 10};
	 
	 
	@Autowired
	private IIncomeService serviceIncome;
	
	@Autowired
	private ITypeIncomeService serviceTypeIncome;
	
	@Autowired
	private ICustomerService service;
	
	@Autowired
	private ICustomerService serviceCustomer;
		
	@RequestMapping("/income")
	private String ListIncome(Model model) {
		model.addAttribute("listIncome", serviceIncome.findAll());
		return "income";
	}
	
	@GetMapping("/income")
	 public ModelAndView ShowIncomePage(@RequestParam("pageSize") Optional<Integer> pageSize,
	            @RequestParam("page") Optional<Integer> page){
	        ModelAndView modelAndView = new ModelAndView("/income");
	        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
	        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
	        Page<Income> listIncome = serviceIncome.listAll(PageRequest.of(evalPage, evalPageSize));
	        Pager pager = new Pager(listIncome.getTotalPages(),listIncome.getNumber(),BUTTONS_TO_SHOW);
	        modelAndView.addObject("listIncome",listIncome);
	        modelAndView.addObject("selectedPageSize", evalPageSize);
	        modelAndView.addObject("pageSizes", PAGE_SIZES);
	        modelAndView.addObject("pager", pager);
	        return modelAndView;
	    }
	
	@RequestMapping(value = "/addincome", method = RequestMethod.POST)
	public String SaveIncome(Model model, Income income, HttpServletRequest request,
			@RequestParam("idIncome") Long idIncome, @RequestParam("date") String date, @RequestParam("value") Double value
			) {
	income.setIdIncome(idIncome);	
	income.setDate(date);
	income.setValue(value);
	String incometypeid = request.getParameter("idTypeIncome");
	TypeIncome typeincome = new TypeIncome();
	typeincome = serviceTypeIncome.findOne(Long.parseLong(incometypeid));
	income.setTypeIncome(typeincome);	
	String idCustomer = request.getParameter("idCustomer");
	Customer customer = new Customer();
	customer = service.findOne(Long.parseLong(idCustomer));
  	income.setCustomer(customer);
  	serviceIncome.save(income);
	return "redirect:/addincome";
	}

			
	@RequestMapping(value = "/editincome/{idIncome}")
	public String ShowEditIncome(Model model, @PathVariable Long idIncome) {
		model.addAttribute("listTypeincome", serviceTypeIncome.findAll());
		model.addAttribute("listcustomer", service.listAll());
		model.addAttribute("income", serviceIncome.findOne(idIncome));
		return "editincome";
	}
	
	@RequestMapping(value = "editincome", method = RequestMethod.POST)
	public String saveEditIncome(Model model, Income income, @RequestParam("date") String date, HttpServletRequest request,
			@RequestParam("value") Double value) {
		income.setDate(date);
		String incometypeid = request.getParameter("idTypeIncome");
		TypeIncome typeincome = new TypeIncome();
		typeincome = serviceTypeIncome.findOne(Long.parseLong(incometypeid));
		income.setTypeIncome(typeincome);	
		String idCustomer = request.getParameter("idCustomer");
		Customer customer = new Customer();
		customer = service.findOne(Long.parseLong(idCustomer));
	  	income.setCustomer(customer);
		serviceIncome.save(income);
		return "redirect:/editincome/" + income.getIdIncome();
	}
	

	@RequestMapping(value = "/deleteincome/{idIncome}")
	public String deleteIncome(Model model, @PathVariable Long idIncome) {
		model.addAttribute("income", serviceIncome.findOne(idIncome));
		serviceIncome.delete(idIncome);
		return "redirect:/income";
	}
	
	
	@RequestMapping(value="/addincome", method = RequestMethod.POST,  params="action=AutomaticMonthlyIncomes")
	public String AutomaticMonthlyInvoices(Model model, HttpServletRequest request) {
		IncomeAlejo();
		IncomeYovanna();
		return "redirect:/income";
	}

	private void IncomeAlejo() {
		LocalDate date = LocalDate.now(); 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	    String strDate = formatter.format(date);  
	    Income IncomeAlejo = new Income();
	    IncomeAlejo.setDate(strDate);
	    Customer customer = new Customer();
        customer = serviceCustomer.findOne((Long.parseLong("12")));
        IncomeAlejo.setCustomer(customer);
        TypeIncome type = new TypeIncome();
        type = serviceTypeIncome.findOne(Long.parseLong("1"));
        IncomeAlejo.setTypeIncome(type);
        Double value = 2213.66;
        IncomeAlejo.setValue(value);
		serviceIncome.save(IncomeAlejo);

	}

	private void IncomeYovanna() {
		LocalDate date = LocalDate.now(); 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	    String strDate = formatter.format(date);  
	    Income IncomeYovanna = new Income();
	    IncomeYovanna.setDate(strDate);
	    Customer customer = new Customer();
        customer = serviceCustomer.findOne((Long.parseLong("13")));
        IncomeYovanna.setCustomer(customer);
        TypeIncome type = new TypeIncome();
        type = serviceTypeIncome.findOne(Long.parseLong("1"));
        IncomeYovanna.setTypeIncome(type);
        Double value = 1892.00;
        IncomeYovanna.setValue(value);
		serviceIncome.save(IncomeYovanna);

	}

}
