package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Bill;
import com.example.demo.model.Customer;
import com.example.demo.model.Item;
import com.example.demo.model.ItemWrapper;
import com.example.demo.model.ItemsCreationWrapper;
import com.example.demo.model.Property;
import com.example.demo.model.ServicesOffered;
import com.example.demo.model.Supplier;
import com.example.demo.model.TypeIncome;
import com.example.demo.model.TypeProperty;
import com.example.demo.model.TypesOfExpenses;
import com.example.demo.service.IBillService;
import com.example.demo.service.ICustomerService;
import com.example.demo.service.IPropertyService;
import com.example.demo.service.IPropertyTypeService;
import com.example.demo.service.IServicesOffered;
import com.example.demo.service.ISupplierService;


@Controller
public class IndexController {

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

    

	@RequestMapping("/addCustomer")
	public String ShowNewCustomrForm(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "addCustomer";
	}

	@RequestMapping("/addsupplier")
	public String ShowNewSupplierForm(Model model) {
		Supplier supplier = new Supplier();
		model.addAttribute("supplier", supplier);
		return "addsupplier";
	}
	
	@RequestMapping("/addservicesoffered")
	public String ShowNewServicesForm(Model model) {
		ServicesOffered services = new ServicesOffered();
		model.addAttribute("service", services);
		return "addservicesoffered";
	}
	

	@RequestMapping("/addTypeIncome")
	public String ShowNewTypeIncomeForm(Model model) {
		TypeIncome Typeincome = new TypeIncome();
		model.addAttribute("TypeIncome", Typeincome);
		return "addTypeIncome";
	}
	
	
	@RequestMapping("/addbill2")
	public String ShowNewBillForm( Model model) {
		ItemsCreationWrapper wrapper = new ItemsCreationWrapper();
		List<Item> items =   new ArrayList<Item>();
		Long inextId =  serviceBill.getNextSeriesInvoiceId();
		Bill bill = new Bill();
		Item item = new Item();
		ItemWrapper itemw = new ItemWrapper();
		List<ItemWrapper> listItemws =   new ArrayList<ItemWrapper>();
	    items.add(item);
	    model.addAttribute("inextId", inextId.toString());
		model.addAttribute("listserv", serviceOffered.listAll());
		model.addAttribute("listsupplier", serviceSupplier.listAll());
		model.addAttribute("bill", bill);
		model.addAttribute("items", items);
		model.addAttribute("item", item);
		model.addAttribute("itemw", itemw);
		model.addAttribute("listItemws", listItemws);
		model.addAttribute("listcustomer", service.listAll());
		model.addAttribute("wrapper", wrapper);
		
		return "addbill2";
	}
	
	@RequestMapping("/additem")
	public String ShowNewItemForm(Model model) {
		ItemWrapper itemw = new ItemWrapper();
		Item item = new Item();
		model.addAttribute("listservices", serviceOffered.listAll());
		model.addAttribute("listsuppliers", serviceSupplier.listAll());
		model.addAttribute("item", item);
		model.addAttribute("itemw", itemw);
		return "additem";
	}
	
	
	@RequestMapping("/addtypexpenses")
	public String ShowNewOfExpensesForm(Model model) {
		TypesOfExpenses expenses = new TypesOfExpenses();
		model.addAttribute("expenses", expenses);
		return "addtypexpenses";
	}
	
	
	@RequestMapping("/addproperty")
	public String ShowNewPropertyForm(Model model) {
		Property property = new Property();
		model.addAttribute("listpropertytype", servicePropertyType.listAll());
		model.addAttribute("property", property);
		return "addproperty";
	}
	
	@RequestMapping("/addtypeproperty")
	public String ShowNewTypePropertyForm(Model model) {
		TypeProperty propertyType = new TypeProperty();
		model.addAttribute("propertyType", propertyType);
		return "addtypeproperty";
	}
	
}


