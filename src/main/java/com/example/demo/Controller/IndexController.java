package com.example.demo.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.model.Bill;
import com.example.demo.model.Category;
import com.example.demo.model.Customer;
import com.example.demo.model.Description;
import com.example.demo.model.Income;
import com.example.demo.model.Item;
import com.example.demo.model.ItemWrapper;
import com.example.demo.model.ItemsCreationWrapper;
import com.example.demo.model.Overview;
import com.example.demo.model.Property;
import com.example.demo.model.ServicesO;
import com.example.demo.model.Supplier;
import com.example.demo.model.TypeIncome;
import com.example.demo.model.TypeProperty;
import com.example.demo.model.TypesOfExpenses;
import com.example.demo.repository.PeriodicityRepository;
import com.example.demo.service.IBillService;
import com.example.demo.service.ICategoryService;
import com.example.demo.service.ICustomerService;
import com.example.demo.service.IDescriptionService;
import com.example.demo.service.IIncomeService;
import com.example.demo.service.IPropertyTypeService;
import com.example.demo.service.IServicesOffered;
import com.example.demo.service.ISupplierService;
import com.example.demo.service.ITypeIncomeService;
import com.example.demo.service.ITypeOfExpensesService;
import  org.springframework.web.bind.annotation.CrossOrigin; 


@Controller
@CrossOrigin (origins = "http: // localhost: 4200" )  
@RequestMapping (value = "/ api" )  
public class IndexController  implements WebMvcConfigurer {
	
	
	 @Override
	    public void addViewControllers(ViewControllerRegistry registry) {
	        registry.addViewController("/results").setViewName("results");
	    }

	@Autowired
	private ICustomerService service;
	
	@Autowired
	private IServicesOffered serviceOffered;
	
	@Autowired
	private ISupplierService serviceSupplier;
	
	@Autowired
	private IBillService serviceBill;
	
	@Autowired
	private IPropertyTypeService servicePropertyType;
	
	@Autowired
	private ITypeIncomeService serviceTypeIncome;
	
	@Autowired
	private ITypeOfExpensesService serviceTypeOfExpenses;
	
	@Autowired
	private PeriodicityRepository repositoryPeriodicity;
	

	@Autowired
	private IIncomeService serviceIncome;
	
	@Autowired
	private ICategoryService serviceCategory;
	
	@Autowired
	private IDescriptionService serviceDescription;
	
	

	static LocalDate localDate = LocalDate.now();
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	String formattedString = localDate.format(formatter);
	
		
    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }

    @GetMapping("/home")
    public String home() {
        return "/home";
    }
    
    @GetMapping("/grapgh")
    public String graph() {
        return "/grapgh";
    }


    @GetMapping("/addCustomer")
	public String ShowNewCustomrForm(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "addCustomer";
	}

    
//    @GetMapping("/addCustomer")
//	public String ShowNewCustomrForm(Model model) {
//		Customer customer = new Customer();
//		model.addAttribute("customer", customer);
//		return "addCustomer";
//	}

	@RequestMapping("/addsupplier")
	public String ShowNewSupplierForm(Model model) {
		Supplier supplier = new Supplier();
		model.addAttribute("supplier", supplier);
		return "addsupplier";
	}
	
	@GetMapping("/addservicesoffered")
	public String ShowNewServicesForm(Model model) {
		ServicesO servicesoffered = new ServicesO();
		model.addAttribute("servicesoffered", servicesoffered);
		return "addservicesoffered";
	}
	

	@RequestMapping("/addTypeIncome")
	public String ShowNewTypeIncomeForm(Model model) {
		TypeIncome typeincome = new TypeIncome();
		model.addAttribute("typeIncome", typeincome);
		return "addTypeIncome";
	}
	
	
	@RequestMapping("/addbill2")
	public String ShowNewBillForm( Model model) {
		ItemsCreationWrapper wrapper = new ItemsCreationWrapper();
		 boolean myBooleanVariable = false;
		List<Item> items =   new ArrayList<Item>();
	//	Long inextId =  serviceBill.getNextSeriesInvoiceId();
		Bill bill = new Bill();
		Item item = new Item();
		ItemWrapper itemw = new ItemWrapper();
		List<ItemWrapper> listItemws =   new ArrayList<ItemWrapper>();
	    items.add(item);
	//    model.addAttribute("inextId", inextId.toString());
		model.addAttribute("listserv", serviceOffered.listAll());
		model.addAttribute("listsupplier", serviceSupplier.findAll());
		model.addAttribute("listdescription", serviceDescription.listAll());
		model.addAttribute("bill", bill);
		model.addAttribute("items", items);
		model.addAttribute("item", item);
		model.addAttribute("itemw", itemw);
		model.addAttribute("listItemws", listItemws);
		model.addAttribute("listcustomer", service.listAll());
		model.addAttribute("listypeOfExpenses", serviceTypeOfExpenses.listAll());
		model.addAttribute("taxesIncluded",  myBooleanVariable);
		model.addAttribute("wrapper", wrapper);		
		return "addbill2";
	}
	
	@RequestMapping("/additem")
	public String ShowNewItemForm(Model model) {
		ItemWrapper itemw = new ItemWrapper();
		Item item = new Item();
		model.addAttribute("listservices", serviceOffered.listAll());
		model.addAttribute("listsuppliers", serviceSupplier.findAll());
		model.addAttribute("item", item);
		model.addAttribute("itemw", itemw);
		return "additem";
	}
	
	
	@RequestMapping("/addtypexpenses")
	public String ShowNewOfExpensesForm(Model model) {
		TypesOfExpenses expenses = new TypesOfExpenses();
		model.addAttribute("listcategory", serviceCategory.listAll());
		model.addAttribute("typexpenses", expenses);
		return "addtypexpenses";
	}
	
	
	@RequestMapping("/addproperty")
	public String ShowNewPropertyForm(Model model) {
		Property property = new Property();
		List<Customer> customersList =   new ArrayList<Customer>();
		model.addAttribute("customersList", customersList);
		model.addAttribute("listcustomer", service.listAll());
		model.addAttribute("listpropertytype", servicePropertyType.listAll());
		model.addAttribute("property", property);
		return "addproperty";
	}
	
	@RequestMapping("/addtypeproperty")
	public String ShowNewTypePropertyForm(Model model) {
		TypeProperty typeproperty = new TypeProperty();
		model.addAttribute("typeproperty", typeproperty);
		return "addtypeproperty";
	}
	
	@RequestMapping("/addCategory")
	public String ShowNewCategoryForm(Model model) {
		Category category = new Category();
		model.addAttribute("category", category);
		return "addCategory";
	}
	
	@RequestMapping("/addOverview")
	public String ShowNewOverviewForm(Model model) {
		Overview overview = new Overview();
		model.addAttribute("overview", overview);
		return "addOverview";
	}
	
	@RequestMapping("/addDescription")
	public String ShowNewDescriptionForm(Model model) {
		Description description = new Description();
		model.addAttribute("description", description);
		return "addDescription";
	}
	
	
	@RequestMapping("/addincome")
	public String ShowNewIncomeForm(Model model) {
		Income income = new Income();
		model.addAttribute("listTypeincome", serviceTypeIncome.findAll());
		model.addAttribute("listcustomer", service.listAll());
		model.addAttribute("income", income);
		return "addincome";
	}
	
	@RequestMapping(value="/budget", method = RequestMethod.GET)
	public String ShowNewBudgetForm(Model model, HttpServletRequest request) {
		int month = localDate.getMonth().getValue();
		Long monthly = Long.parseLong(Integer.toString(month));
		List<Income> incomeList = new ArrayList<Income>();
		List<Bill> expensesList = new ArrayList<Bill>();
		model.addAttribute("periodicityList",repositoryPeriodicity.findAll());
		incomeList = serviceIncome.getIncomeMonthly(monthly);
		expensesList =  serviceBill.getExpensesMonthly(monthly);
		model.addAttribute("totalInvoice", serviceBill.TotalInvoiceBudget(expensesList));
		model.addAttribute("totalIncome", serviceIncome.TotalIncomeBudget(incomeList));
		model.addAttribute("difference", calulerdireferen(serviceBill.TotalInvoiceBudget(expensesList),serviceIncome.TotalIncomeBudget(incomeList)));
		model.addAttribute("incomeList", incomeList);
		model.addAttribute("expensesList", expensesList);	
		return "budget";
	}

	
	@RequestMapping(value="/budget", method = RequestMethod.GET,  params="action=Biannual")
	public String ShowNewBudgetFormBiannual(Model model, HttpServletRequest request) {
		List<Income> incomeList = new ArrayList<Income>();
		List<Bill> expensesList = new ArrayList<Bill>();
		incomeList = serviceIncome.getIncomeBiannual();
		expensesList =  serviceBill.getExpensesBiannual();
		model.addAttribute("totalInvoice", serviceBill.TotalInvoiceBudget(expensesList));
		model.addAttribute("totalIncome", serviceIncome.TotalIncomeBudget(incomeList));
		model.addAttribute("difference", calulerdireferen(serviceBill.TotalInvoiceBudget(expensesList),serviceIncome.TotalIncomeBudget(incomeList)));
		model.addAttribute("incomeList", incomeList);
		model.addAttribute("expensesList", expensesList);	
		return "budget";
	}
	
	
	@RequestMapping(value="/budget", method = RequestMethod.GET,  params="action=Monthly")
	public String ShowNewBudgetFormMonthly(Model model, HttpServletRequest request) {
		int month = localDate.getMonth().getValue();
		Long monthly = Long.parseLong(Integer.toString(month));
		List<Income> incomeList = new ArrayList<Income>();
		List<Bill> expensesList = new ArrayList<Bill>();
		incomeList = serviceIncome.getIncomeMonthly(monthly);
		expensesList =  serviceBill.getExpensesMonthly(monthly);
		model.addAttribute("totalInvoice", serviceBill.TotalInvoiceBudget(expensesList));
		model.addAttribute("totalIncome", serviceIncome.TotalIncomeBudget(incomeList));
		model.addAttribute("difference", calulerdireferen(serviceBill.TotalInvoiceBudget(expensesList),serviceIncome.TotalIncomeBudget(incomeList)));
		model.addAttribute("incomeList", incomeList);
		model.addAttribute("expensesList", expensesList);	
		return "budget";
	}
	
	@RequestMapping(value="/budget", method = RequestMethod.GET,  params="action=Quartely")
	public String ShowNewBudgetFormQuartely(Model model, HttpServletRequest request) {
		List<Income> incomeList = new ArrayList<Income>();
		List<Bill> expensesList = new ArrayList<Bill>();
		incomeList = serviceIncome.getExpensesQuartely();
		expensesList =  serviceBill.getExpensesQuartely();
		model.addAttribute("totalInvoice", serviceBill.TotalInvoiceBudget(expensesList));
		model.addAttribute("totalIncome", serviceIncome.TotalIncomeBudget(incomeList));
		model.addAttribute("difference", calulerdireferen(serviceBill.TotalInvoiceBudget(expensesList),serviceIncome.TotalIncomeBudget(incomeList)));
		model.addAttribute("incomeList", incomeList);
		model.addAttribute("expensesList", expensesList);	
		return "budget";
	}
	
	@GetMapping("/displayBarGraph")
	public String barGraph(Model model) {
		Map<String, Integer> surveyMap = new LinkedHashMap<>();
		surveyMap.put("Bills & utilites", 40);
		surveyMap.put("Auto & Transport", 25);
		surveyMap.put("Food & dining", 20);
		surveyMap.put("Shopping", 15);
		surveyMap.put("Entertaiment", 8);
		model.addAttribute("surveyMap", surveyMap);
		return "barGraph";
	}

	@GetMapping("/displayPieChart")
	public String pieChart(Model model) {
		model.addAttribute("pass", 50);
		model.addAttribute("fail", 50);
		return "pieChart";
	}
	
	private Double calulerdireferen(Double totalInvoiceBudget, Double totalIncomeBudget) {
		int totadofference ;	
		totadofference =  (int) (totalIncomeBudget - totalInvoiceBudget); 
	return  Double.valueOf(totadofference);
	}
}


