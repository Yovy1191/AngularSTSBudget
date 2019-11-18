package com.example.demo.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Bill;
import com.example.demo.model.Customer;
import com.example.demo.model.Item;
import com.example.demo.model.ItemId;
import com.example.demo.model.ItemWrapper;
import com.example.demo.model.ItemsCreationWrapper;
import com.example.demo.model.Pager;
import com.example.demo.model.ServicesO;
import com.example.demo.model.Supplier;
import com.example.demo.model.TypesOfExpenses;
import com.example.demo.service.IBillService;
import com.example.demo.service.ICustomerService;
import com.example.demo.service.IItemService;
import com.example.demo.service.IServicesOffered;
import com.example.demo.service.ISupplierService;
import com.example.demo.service.ITypeOfExpensesService;

@Controller
public class BillController {

	 private static final int BUTTONS_TO_SHOW = 3;
	 private static final int INITIAL_PAGE = 0;
	 private static final int INITIAL_PAGE_SIZE = 5;
	 private static final int[] PAGE_SIZES = { 5, 10};
	
	static LocalDate localDate = LocalDate.now();

	@Autowired
	private IBillService serviceBill;

	@Autowired
	private ICustomerService serviceCustomer;

	@Autowired
	private IServicesOffered serviceOffered;

	@Autowired
	private ISupplierService serviceSupplier;

	@Autowired
	private IItemService serviceItem;
	
	@Autowired
	private ITypeOfExpensesService serviceTypeOfExpenses;
	
	public static int idCounter = 1;

	@RequestMapping("/bill")
	private String ShowFormListBill(Model model,@RequestParam(defaultValue="0") int page ) {
		model.addAttribute("listbill", serviceBill.findAll());
		return "bill";
	}
	
	@GetMapping("/bill")
	 public ModelAndView ShowBillPage(@RequestParam("pageSize") Optional<Integer> pageSize,
	            @RequestParam("page") Optional<Integer> page){
	        ModelAndView modelAndView = new ModelAndView("/bill");
	        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
	        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
	        Page<Bill> listbill = serviceBill.listAll(PageRequest.of(evalPage, evalPageSize));
	        Pager pager = new Pager(listbill.getTotalPages(),listbill.getNumber(),BUTTONS_TO_SHOW);
	        modelAndView.addObject("listbill",listbill);
	        modelAndView.addObject("selectedPageSize", evalPageSize);
	        modelAndView.addObject("pageSizes", PAGE_SIZES);
	        modelAndView.addObject("pager", pager);
	        return modelAndView;
	    }

	@RequestMapping(value = "/addbill2", method = RequestMethod.POST)
	public String SaveBill(@ModelAttribute ItemsCreationWrapper form, Model model,  ItemWrapper itemw,  HttpServletRequest request) {
		Bill bill = new Bill();
		Customer customer = new Customer();
		ServicesO service = new ServicesO();
		TypesOfExpenses expenses =  new TypesOfExpenses();
		List<Item> items = new ArrayList<Item>();
		Supplier supplier = new Supplier();
		String date = request.getParameter("date");
		bill.setDate(date);
		String customerid = request.getParameter("idCustomer");
		customer = serviceCustomer.findOne(Long.parseLong(customerid));
		bill.setCustomer(customer);
		String expensesTypeId = request.getParameter("idTypeOfExpenses");
		expenses = serviceTypeOfExpenses.findOne(Long.parseLong(expensesTypeId));
		bill.setTypeexpenses(expenses);
		String serviceid = request.getParameter("idService");
		service = serviceOffered.findOne(Long.parseLong(serviceid)); 
		String supplierid = request.getParameter("idSupplier");
		supplier = serviceSupplier.findOne(Long.parseLong(supplierid));
		Long inextId =  serviceBill.getNextSeriesInvoiceId() ;
		String itemid = request.getParameter("ItemId");
		itemw.setItemId(Long.parseLong(itemid));
		itemw.setInvoiceId(inextId);
		itemw.setSupplier(supplier);
		itemw.setServices(service);
		Item itemsave = serviceItem.save(itemw.getInvoiceId(), itemw.getItemId(), itemw.getDescription(),
				itemw.getQte(), itemw.getPrice(), itemw.getTotal(), itemw.getServices(), itemw.getSupplier());
		items.add(itemsave);
		bill.setItems(items);
		String sub_total = request.getParameter("sub_total");
		bill.setSub_total(Double.parseDouble(sub_total));
		String tps = request.getParameter("tps");
		bill.setTps(Double.parseDouble(tps));
		String tvq = request.getParameter("tvq");
		bill.setTvq(Double.parseDouble(tvq));
		String total = request.getParameter("total");
		bill.setTotal(Double.parseDouble(total));
		serviceBill.save(bill);
		return "redirect:/addbill2";
	}
	
	
	@RequestMapping(value="/addbill2", method = RequestMethod.POST,  params="action=AutomaticMonthlyInvoices")
	public String AutomaticMonthlyInvoices(Model model, HttpServletRequest request) {
		InvoiceCarMonthly();
          InvoiceCeliMonthly();
          InvoicePretAlejo();
          InvoicePretYovanna();
          InvoiceRentMonthly();
          InvoiceNetflixMonthly();
		return "redirect:/bill";
	}

	private Bill InvoicePretYovanna() {
		Bill billPretYovanna = new Bill();
		Long id = serviceBill.getNextSeriesInvoiceId() ;		
	    billPretYovanna.setInvoiceId(id);
        LocalDate date = LocalDate.now(); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String strDate = formatter.format(date);  
        billPretYovanna.setDate(strDate);
        Customer customer = new Customer();
        customer = serviceCustomer.findOne((Long.parseLong("13")));
        billPretYovanna.setCustomer(customer);
        Double sub_total = 125.92;
        billPretYovanna.setSub_total(sub_total);
		Double tps = 0.00;
		billPretYovanna.setTps(tps);
		Double tvq =  0.00;
		billPretYovanna.setTvq(tvq );
		billPretYovanna.setTotal(sub_total);
		TypesOfExpenses typeEx = new TypesOfExpenses();
		typeEx = serviceTypeOfExpenses.findOne((Long.parseLong("7")));
		billPretYovanna.setTypeexpenses(typeEx);
		Item itemYovanna = new Item();
		ItemId idItem = new ItemId(billPretYovanna.getInvoiceId(), (Long.parseLong("1")));
		itemYovanna.setIdItem(idItem);
		itemYovanna.setDescription("CELI");
		itemYovanna.setQte((Double.parseDouble("1")));
		itemYovanna.setPrice(sub_total);
		itemYovanna.setTotal(sub_total);
		ServicesO service = new ServicesO();
		service = serviceOffered.findOne((Long.parseLong("15")));
		itemYovanna.setServices(service);
		Supplier supplier = new Supplier();
		supplier = serviceSupplier.findOne((Long.parseLong("2")));
		itemYovanna.setSupplier(supplier); 
		serviceItem.save(id, (Long.parseLong("1")), "PRET YB", (Double.parseDouble("1")), sub_total, sub_total, service, supplier);
		List<Item> items =  new ArrayList<Item>() ;
		items.add(itemYovanna);
		billPretYovanna.setItems(items);
		serviceBill.save(billPretYovanna);
		return billPretYovanna;
	}

	private Bill InvoicePretAlejo() {
		Bill billPretAlejo = new Bill();
		Long id = serviceBill.getNextSeriesInvoiceId();
        billPretAlejo.setInvoiceId(id);
        LocalDate date = LocalDate.now(); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String strDate = formatter.format(date);  
        billPretAlejo.setDate(strDate);
        Customer customer = new Customer();
        customer = serviceCustomer.findOne((Long.parseLong("12")));
        billPretAlejo.setCustomer(customer);
        Double sub_total = 105.71;
        billPretAlejo.setSub_total(sub_total);
		Double tps = 0.00;
		billPretAlejo.setTps(tps);
		Double tvq =  0.00;
		billPretAlejo.setTvq(tvq );
		billPretAlejo.setTotal(sub_total);
		TypesOfExpenses typeEx = new TypesOfExpenses();
		typeEx = serviceTypeOfExpenses.findOne((Long.parseLong("7")));
		billPretAlejo.setTypeexpenses(typeEx);
		Item itemAlejo = new Item();
		ItemId idItem = new ItemId(billPretAlejo.getInvoiceId(), (Long.parseLong("1")));
		itemAlejo.setIdItem(idItem);
		itemAlejo.setDescription("CELI");
		itemAlejo.setQte((Double.parseDouble("1")));
		itemAlejo.setPrice(sub_total);
		itemAlejo.setTotal(sub_total);
		ServicesO service = new ServicesO();
		service = serviceOffered.findOne((Long.parseLong("15")));
		itemAlejo.setServices(service);
		Supplier supplier = new Supplier();
		supplier = serviceSupplier.findOne((Long.parseLong("2")));
		itemAlejo.setSupplier(supplier); 
		serviceItem.save(id, (Long.parseLong("1")), "PRET ALEJO", (Double.parseDouble("1")), sub_total, sub_total, service, supplier);
		List<Item> items =  new ArrayList<Item>() ;
		items.add(itemAlejo);
		billPretAlejo.setItems(items);
		serviceBill.save(billPretAlejo);
		return billPretAlejo;
	}

	private Bill InvoiceCeliMonthly() {
		Bill billCeli = new Bill();
		Long id = serviceBill.getNextSeriesInvoiceId();
        billCeli.setInvoiceId(id);
        LocalDate date = LocalDate.now(); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String strDate = formatter.format(date);  
        billCeli.setDate(strDate);
        Customer customer = new Customer();
        customer = serviceCustomer.findOne((Long.parseLong("15")));
        billCeli.setCustomer(customer);
        Double sub_total = 50.00;
        billCeli.setSub_total(sub_total);
		Double tps = 0.00;
		billCeli.setTps(tps);
		Double tvq =  0.00;
		billCeli.setTvq(tvq );
		billCeli.setTotal(sub_total);
		TypesOfExpenses typeEx = new TypesOfExpenses();
		typeEx = serviceTypeOfExpenses.findOne((Long.parseLong("78")));
		billCeli.setTypeexpenses(typeEx);
		Item itemCeli = new Item();
		ItemId idItem = new ItemId(billCeli.getInvoiceId(), (Long.parseLong("1")));
		itemCeli.setIdItem(idItem);
		itemCeli.setDescription("CELI");
		itemCeli.setQte((Double.parseDouble("1")));
		itemCeli.setPrice(sub_total);
		itemCeli.setTotal(sub_total);
		ServicesO service = new ServicesO();
		service = serviceOffered.findOne((Long.parseLong("15")));
		itemCeli.setServices(service);
		Supplier supplier = new Supplier();
		supplier = serviceSupplier.findOne((Long.parseLong("2")));
		itemCeli.setSupplier(supplier); 
		serviceItem.save(id, (Long.parseLong("1")), "CONTRIBUTION CELI", (Double.parseDouble("1")), sub_total, sub_total, service, supplier);
		List<Item> items =  new ArrayList<Item>() ;
		items.add(itemCeli);
		billCeli.setItems(items);
		serviceBill.save(billCeli);
		return billCeli;
	}

	private Bill InvoiceCarMonthly() {
		Bill billCar = new Bill();
		Long id = serviceBill.getNextSeriesInvoiceId();
		System.out.println("value " + id);
        billCar.setInvoiceId(id);
        LocalDate date = LocalDate.now(); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String strDate = formatter.format(date);  
        billCar.setDate(strDate);
        Customer customer = new Customer();
        customer = serviceCustomer.findOne((Long.parseLong("15")));
        billCar.setCustomer(customer);
        Double sub_total = 256.13;
		billCar.setSub_total(sub_total);
		Double tps = 0.00;
		billCar.setTps(tps);
		Double tvq =  0.00;
		billCar.setTvq(tvq );
		billCar.setTotal(sub_total);
		TypesOfExpenses typeEx = new TypesOfExpenses();
		typeEx = serviceTypeOfExpenses.findOne((Long.parseLong("17")));
		billCar.setTypeexpenses(typeEx);
		Item itemCar = new Item();
		ItemId idItem = new ItemId(serviceBill.getNextSeriesInvoiceId(), (Long.parseLong("1")));
		itemCar.setIdItem(idItem);
		itemCar.setDescription("TOYOTA CREDIT");
		itemCar.setQte((Double.parseDouble("1")));
		itemCar.setPrice(sub_total);
		itemCar.setTotal(sub_total);
		ServicesO service = new ServicesO();
		service = serviceOffered.findOne((Long.parseLong("15")));
		itemCar.setServices(service);
		Supplier supplier = new Supplier();
		supplier = serviceSupplier.findOne((Long.parseLong("2")));
		itemCar.setSupplier(supplier);
		serviceItem.save(id, (Long.parseLong("1")), "LOYER AUTOMOBILE TOYOTA CREDIT", (Double.parseDouble("1")), sub_total, sub_total, service, supplier);
		List<Item> items =  new ArrayList<Item>() ;
		items.add(itemCar);
		billCar.setItems(items);
		serviceBill.save(billCar);
		return billCar;
		
	}
	
	private Bill InvoiceRentMonthly() {
		Bill billCar = new Bill();
		Long id = serviceBill.getNextSeriesInvoiceId();
        billCar.setInvoiceId(id);
        LocalDate date = LocalDate.now(); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String strDate = formatter.format(date);  
        billCar.setDate(strDate);
        Customer customer = new Customer();
        customer = serviceCustomer.findOne((Long.parseLong("15")));
        billCar.setCustomer(customer);
        Double sub_total = 720.00;
		billCar.setSub_total(sub_total);
		Double tps = 0.00;
		billCar.setTps(tps);
		Double tvq =  0.00;
		billCar.setTvq(tvq );
		billCar.setTotal(sub_total);
		TypesOfExpenses typeEx = new TypesOfExpenses();
		typeEx = serviceTypeOfExpenses.findOne((Long.parseLong("79")));
		billCar.setTypeexpenses(typeEx);
		Item itemCar = new Item();
		ItemId idItem = new ItemId(serviceBill.getNextSeriesInvoiceId(), (Long.parseLong("1")));
		itemCar.setIdItem(idItem);
		itemCar.setDescription("lES IMMEUBLES - APP 101");
		itemCar.setQte((Double.parseDouble("1")));
		itemCar.setPrice(sub_total);
		itemCar.setTotal(sub_total);
		ServicesO service = new ServicesO();
		service = serviceOffered.findOne((Long.parseLong("15")));
		itemCar.setServices(service);
		Supplier supplier = new Supplier();
		supplier = serviceSupplier.findOne((Long.parseLong("2")));
		itemCar.setSupplier(supplier);
		serviceItem.save(id, (Long.parseLong("1")), "lES IMMEUBLES - APP 101", (Double.parseDouble("1")), sub_total, sub_total, service, supplier);
		List<Item> items =  new ArrayList<Item>() ;
		items.add(itemCar);
		billCar.setItems(items);
		serviceBill.save(billCar);
		return billCar;
		
	}
	private Bill InvoiceNetflixMonthly() {
		Bill billCar = new Bill();
		Long id = serviceBill.getNextSeriesInvoiceId();
        billCar.setInvoiceId(id);
        LocalDate date = LocalDate.now(); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String strDate = formatter.format(date);  
        billCar.setDate(strDate);
        Customer customer = new Customer();
        customer = serviceCustomer.findOne((Long.parseLong("15")));
        billCar.setCustomer(customer);
        Double sub_total = 15.39;
		billCar.setSub_total(sub_total);
		Double tps = 0.00;
		billCar.setTps(tps);
		Double tvq =  0.00;
		billCar.setTvq(tvq );
		billCar.setTotal(sub_total);
		TypesOfExpenses typeEx = new TypesOfExpenses();
		typeEx = serviceTypeOfExpenses.findOne((Long.parseLong("1")));
		billCar.setTypeexpenses(typeEx);
		Item itemCar = new Item();
		ItemId idItem = new ItemId(serviceBill.getNextSeriesInvoiceId(), (Long.parseLong("1")));
		itemCar.setIdItem(idItem);
		itemCar.setDescription("NEXTFLIX");
		itemCar.setQte((Double.parseDouble("1")));
		itemCar.setPrice(sub_total);
		itemCar.setTotal(sub_total);
		ServicesO service = new ServicesO();
		service = serviceOffered.findOne((Long.parseLong("15")));
		itemCar.setServices(service);
		Supplier supplier = new Supplier();
		supplier = serviceSupplier.findOne((Long.parseLong("2")));
		itemCar.setSupplier(supplier);
		serviceItem.save(id, (Long.parseLong("1")), "NEXTFLIX", (Double.parseDouble("1")), sub_total, sub_total, service, supplier);
		List<Item> items =  new ArrayList<Item>() ;
		items.add(itemCar);
		billCar.setItems(items);
		serviceBill.save(billCar);
		return billCar;
		
	}

}
