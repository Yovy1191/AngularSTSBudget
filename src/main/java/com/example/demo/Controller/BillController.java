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
import com.example.demo.model.Description;
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
import com.example.demo.service.IDescriptionService;
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
	private IDescriptionService serviceDescription;
	
	
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
		Description description = new Description();
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
		String descriptionId = request.getParameter("descriptionId");
		description = serviceDescription.findOne(Long.parseLong(descriptionId));
		Long inextId =  serviceBill.getNextSeriesInvoiceId() ;
		String itemid = request.getParameter("ItemId");
		String price = request.getParameter("price");
		String sub_total = request.getParameter("total");
		bill.setSub_total(Double.parseDouble(sub_total));
		String taxesIn = request.getParameter("taxesIncluded");
		boolean taxesIncluded = (Boolean.parseBoolean(taxesIn));
		bill.setTaxesIncluded(taxesIncluded);
		if (bill.isTaxesIncluded() == false) {
			String tps = request.getParameter("tps");
			bill.setTps(Double.parseDouble(tps));
			String tvq = request.getParameter("tvq");
			bill.setTvq(Double.parseDouble(tvq));
        }
		else
		{
			bill.setTaxesIncluded(true);
			bill.setTps(Double.parseDouble("0"));
			bill.setTvq(Double.parseDouble("0"));
        }
		
		bill.setTotal(bill.getSub_total()+ bill.getTps()+ bill.getTvq());
		itemw.setItemId(Long.parseLong(itemid));
		itemw.setInvoiceId(inextId);
		itemw.setSupplier(supplier);
		itemw.setServices(service);
		itemw.setDescription(description);
		itemw.setQte(Double.parseDouble(request.getParameter("qte")));
		itemw.setPrice(Double.parseDouble(price));
		itemw.setTotal(bill.getTotal());
		Item itemsave = serviceItem.save(itemw.getInvoiceId(), itemw.getItemId(), itemw.getDescription(),
				itemw.getQte(), itemw.getPrice(), itemw.getSubtotal(), itemw.getTotal(), itemw.getServices(), itemw.getSupplier());
		items.add(itemsave);
		bill.setItems(items);
		
		serviceBill.save(bill);
		return "redirect:/bill";
	}
	
	
	@RequestMapping(value="/addbill2", method = RequestMethod.POST,  params="action=AutomaticMonthlyInvoices")
	public String AutomaticMonthlyInvoices(Model model, HttpServletRequest request) {
	      InvoiceCarMonthly();
          InvoiceCeliMonthly();
          InvoicePretAlejo();
          InvoicePretYovanna();
          InvoiceRentMonthly();
          InvoiceNetflixMonthly();
          InvoiceDonationsMonthly();
          InvoiceDiversMonthly();
          InvoiceXimenaMonthly();
          InvoiceElectricityMonthly() ;
          InvoiceVacationMonthly() ;
          InvoiceVetementsMonthly();
          InvoiceLoisirMonthly() ;
          InvoiceUrgencesMonthly();
          InvoiceGyMMonthly() ;
          InvoiceConductMonthly();
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
		Description description = new Description();
		description = serviceDescription.findOne(Long.parseLong("4"));
		billPretYovanna.setTypeexpenses(typeEx);
		Item itemYovanna = new Item();
		ItemId idItem = new ItemId(billPretYovanna.getInvoiceId(), (Long.parseLong("1")));
		itemYovanna.setIdItem(idItem);
		itemYovanna.setDescription(description);
		itemYovanna.setQte((Double.parseDouble("1")));
		itemYovanna.setPrice(sub_total);
		itemYovanna.setTotal(sub_total);
		ServicesO service = new ServicesO();
		service = serviceOffered.findOne((Long.parseLong("15")));
		itemYovanna.setServices(service);
		Supplier supplier = new Supplier();
		supplier = serviceSupplier.findOne((Long.parseLong("2")));
		itemYovanna.setSupplier(supplier); 
		serviceItem.save(id, (Long.parseLong("1")), description, (Double.parseDouble("1")), sub_total, sub_total, sub_total,  service, supplier);
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
		Description description = new Description();
		description = serviceDescription.findOne(Long.parseLong("5"));
		itemAlejo.setIdItem(idItem);
		itemAlejo.setDescription(description);
		itemAlejo.setQte((Double.parseDouble("1")));
		itemAlejo.setPrice(sub_total);
		itemAlejo.setTotal(sub_total);
		ServicesO service = new ServicesO();
		service = serviceOffered.findOne((Long.parseLong("15")));
		itemAlejo.setServices(service);
		Supplier supplier = new Supplier();
		supplier = serviceSupplier.findOne((Long.parseLong("2")));
		itemAlejo.setSupplier(supplier); 
		serviceItem.save(id, (Long.parseLong("1")), description, (Double.parseDouble("1")), sub_total, sub_total, sub_total, service, supplier);
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
		Description description = new Description();
		description = serviceDescription.findOne(Long.parseLong("3"));
		billCeli.setTypeexpenses(typeEx);
		Item itemCeli = new Item();
		ItemId idItem = new ItemId(billCeli.getInvoiceId(), (Long.parseLong("1")));
		itemCeli.setIdItem(idItem);
		itemCeli.setDescription(description);
		itemCeli.setQte((Double.parseDouble("1")));
		itemCeli.setPrice(sub_total);
		itemCeli.setTotal(sub_total);
		ServicesO service = new ServicesO();
		service = serviceOffered.findOne((Long.parseLong("15")));
		itemCeli.setServices(service);
		Supplier supplier = new Supplier();
		supplier = serviceSupplier.findOne((Long.parseLong("2")));
		itemCeli.setSupplier(supplier); 
		serviceItem.save(id, (Long.parseLong("1")), description, (Double.parseDouble("1")), sub_total, sub_total, sub_total, service, supplier);
		List<Item> items =  new ArrayList<Item>() ;
		items.add(itemCeli);
		billCeli.setItems(items);
		serviceBill.save(billCeli);
		return billCeli;
	}

	private Bill InvoiceCarMonthly() {
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
        Double sub_total = 256.13;
		billCar.setSub_total(sub_total);
		Double tps = 0.00;
		billCar.setTps(tps);
		Double tvq =  0.00;
		billCar.setTvq(tvq );
		billCar.setTotal(sub_total);
		TypesOfExpenses typeEx = new TypesOfExpenses();
		typeEx = serviceTypeOfExpenses.findOne((Long.parseLong("17")));
		Description description = new Description();
		description = serviceDescription.findOne(Long.parseLong("6"));
		billCar.setTypeexpenses(typeEx);
		Item itemCar = new Item();
		ItemId idItem = new ItemId(serviceBill.getNextSeriesInvoiceId(), (Long.parseLong("1")));
		itemCar.setIdItem(idItem);
		itemCar.setDescription(description);
		itemCar.setQte((Double.parseDouble("1")));
		itemCar.setPrice(sub_total);
		itemCar.setTotal(sub_total);
		ServicesO service = new ServicesO();
		service = serviceOffered.findOne((Long.parseLong("15")));
		itemCar.setServices(service);
		Supplier supplier = new Supplier();
		supplier = serviceSupplier.findOne((Long.parseLong("2")));
		itemCar.setSupplier(supplier);
		serviceItem.save(id, (Long.parseLong("1")), description, (Double.parseDouble("1")), sub_total, sub_total, sub_total, service, supplier);
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
        Description description = new Description();
		description = serviceDescription.findOne(Long.parseLong("7"));
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
		itemCar.setDescription(description);
		itemCar.setQte((Double.parseDouble("1")));
		itemCar.setPrice(sub_total);
		itemCar.setTotal(sub_total);
		ServicesO service = new ServicesO();
		service = serviceOffered.findOne((Long.parseLong("15")));
		itemCar.setServices(service);
		Supplier supplier = new Supplier();
		supplier = serviceSupplier.findOne((Long.parseLong("2")));
		itemCar.setSupplier(supplier);
		serviceItem.save(id, (Long.parseLong("1")), description, (Double.parseDouble("1")), sub_total, sub_total, sub_total, service, supplier);
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
		Description description = new Description();
		description = serviceDescription.findOne(Long.parseLong("8"));
		billCar.setTypeexpenses(typeEx);
		Item itemCar = new Item();
		ItemId idItem = new ItemId(serviceBill.getNextSeriesInvoiceId(), (Long.parseLong("1")));
		itemCar.setIdItem(idItem);
		itemCar.setDescription(description);
		itemCar.setQte((Double.parseDouble("1")));
		itemCar.setPrice(sub_total);
		itemCar.setTotal(sub_total);
		ServicesO service = new ServicesO();
		service = serviceOffered.findOne((Long.parseLong("15")));
		itemCar.setServices(service);
		Supplier supplier = new Supplier();
		supplier = serviceSupplier.findOne((Long.parseLong("2")));
		itemCar.setSupplier(supplier);
		serviceItem.save(id, (Long.parseLong("1")), description, (Double.parseDouble("1")), sub_total, sub_total, sub_total, service, supplier);
		List<Item> items =  new ArrayList<Item>() ;
		items.add(itemCar);
		billCar.setItems(items);
		serviceBill.save(billCar);
		return billCar;
		
	}
	
	private Bill InvoiceDonationsMonthly() {
		Bill billDon = new Bill();
		Long id = serviceBill.getNextSeriesInvoiceId();
		billDon.setInvoiceId(id);
        LocalDate date = LocalDate.now(); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String strDate = formatter.format(date);  
        billDon.setDate(strDate);
        Customer customer = new Customer();
        customer = serviceCustomer.findOne((Long.parseLong("15")));
        billDon.setCustomer(customer);
        Double sub_total = 35.00;
        billDon.setSub_total(sub_total);
		Double tps = 0.00;
		billDon.setTps(tps);
		Double tvq =  0.00;
		billDon.setTvq(tvq );
		billDon.setTotal(sub_total);
		TypesOfExpenses typeEx = new TypesOfExpenses();
		typeEx = serviceTypeOfExpenses.findOne((Long.parseLong("8")));
		Description description = new Description();
		description = serviceDescription.findOne(Long.parseLong("10"));
		billDon.setTypeexpenses(typeEx);
		Item itemDon = new Item();
		ItemId idItem = new ItemId(serviceBill.getNextSeriesInvoiceId(), (Long.parseLong("1")));
		itemDon.setIdItem(idItem);
		itemDon.setDescription(description);
		itemDon.setQte((Double.parseDouble("1")));
		itemDon.setPrice(sub_total);
		itemDon.setTotal(sub_total);
		ServicesO service = new ServicesO();
		service = serviceOffered.findOne((Long.parseLong("8")));
		itemDon.setServices(service);
		Supplier supplier = new Supplier();
		supplier = serviceSupplier.findOne((Long.parseLong("27")));
		itemDon.setSupplier(supplier);
		serviceItem.save(id, (Long.parseLong("1")), description, (Double.parseDouble("1")), sub_total, sub_total, sub_total, service, supplier);
		List<Item> items =  new ArrayList<Item>() ;
		items.add(itemDon);
		billDon.setItems(items);
		serviceBill.save(billDon);
		return billDon;
		
	}
	
	private Bill InvoiceDiversMonthly() {
		Bill billDivers = new Bill();
		Long id = serviceBill.getNextSeriesInvoiceId();
		billDivers.setInvoiceId(id);
        LocalDate date = LocalDate.now(); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String strDate = formatter.format(date);  
        billDivers.setDate(strDate);
        Customer customer = new Customer();
        customer = serviceCustomer.findOne((Long.parseLong("15")));
        billDivers.setCustomer(customer);
        Double sub_total = 55.00;
        billDivers.setSub_total(sub_total);
		Double tps = 0.00;
		billDivers.setTps(tps);
		Double tvq =  0.00;
		billDivers.setTvq(tvq );
		billDivers.setTotal(sub_total);
		TypesOfExpenses typeEx = new TypesOfExpenses();
		typeEx = serviceTypeOfExpenses.findOne((Long.parseLong("9")));
		Description description = new Description();
		description = serviceDescription.findOne(Long.parseLong("11"));
		billDivers.setTypeexpenses(typeEx);
		Item itemDivers = new Item();
		ItemId idItem = new ItemId(serviceBill.getNextSeriesInvoiceId(), (Long.parseLong("1")));
		itemDivers.setIdItem(idItem);
		itemDivers.setDescription(description);
		itemDivers.setQte((Double.parseDouble("1")));
		itemDivers.setPrice(sub_total);
		itemDivers.setTotal(sub_total);
		ServicesO service = new ServicesO();
		service = serviceOffered.findOne((Long.parseLong("8")));
		itemDivers.setServices(service);
		Supplier supplier = new Supplier();
		supplier = serviceSupplier.findOne((Long.parseLong("28")));
		itemDivers.setSupplier(supplier);
		serviceItem.save(id, (Long.parseLong("1")), description, (Double.parseDouble("1")), sub_total, sub_total, sub_total, service, supplier);
		List<Item> items =  new ArrayList<Item>() ;
		items.add(itemDivers);
		billDivers.setItems(items);
		serviceBill.save(billDivers);
		return billDivers;
		
	}
	private Bill InvoiceXimenaMonthly() {
		Bill billGirl = new Bill();
		Long id = serviceBill.getNextSeriesInvoiceId();
		billGirl.setInvoiceId(id);
        LocalDate date = LocalDate.now(); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String strDate = formatter.format(date);  
        billGirl.setDate(strDate);
        Customer customer = new Customer();
        customer = serviceCustomer.findOne((Long.parseLong("12")));
        billGirl.setCustomer(customer);
        Double sub_total = 80.00;
        billGirl.setSub_total(sub_total);
		Double tps = 0.00;
		billGirl.setTps(tps);
		Double tvq =  0.00;
		billGirl.setTvq(tvq );
		billGirl.setTotal(sub_total);
		TypesOfExpenses typeEx = new TypesOfExpenses();
		typeEx = serviceTypeOfExpenses.findOne((Long.parseLong("2")));
		Description description = new Description();
		description = serviceDescription.findOne(Long.parseLong("15"));
		billGirl.setTypeexpenses(typeEx);
		Item itemGirl = new Item();
		ItemId idItem = new ItemId(serviceBill.getNextSeriesInvoiceId(), (Long.parseLong("1")));
		itemGirl.setIdItem(idItem);
		itemGirl.setDescription(description);
		itemGirl.setQte((Double.parseDouble("1")));
		itemGirl.setPrice(sub_total);
		itemGirl.setTotal(sub_total);
		ServicesO service = new ServicesO();
		service = serviceOffered.findOne((Long.parseLong("8")));
		itemGirl.setServices(service);
		Supplier supplier = new Supplier();
		supplier = serviceSupplier.findOne((Long.parseLong("28")));
		itemGirl.setSupplier(supplier);
		serviceItem.save(id, (Long.parseLong("1")), description, (Double.parseDouble("1")), sub_total, sub_total, sub_total, service, supplier);
		List<Item> items =  new ArrayList<Item>() ;
		items.add(itemGirl);
		billGirl.setItems(items);
		serviceBill.save(billGirl);
		return billGirl;
		
	}
	
	private Bill InvoiceConductMonthly() {
		Bill billConduct = new Bill();
		Long id = serviceBill.getNextSeriesInvoiceId();
		billConduct.setInvoiceId(id);
        LocalDate date = LocalDate.now(); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String strDate = formatter.format(date);  
        billConduct.setDate(strDate);
        Customer customer = new Customer();
        customer = serviceCustomer.findOne((Long.parseLong("15")));
        billConduct.setCustomer(customer);
        Double sub_total = 20.00;
        billConduct.setSub_total(sub_total);
		Double tps = 0.00;
		billConduct.setTps(tps);
		Double tvq =  0.00;
		billConduct.setTvq(tvq );
		billConduct.setTotal(sub_total);
		TypesOfExpenses typeEx = new TypesOfExpenses();
		typeEx = serviceTypeOfExpenses.findOne((Long.parseLong("3")));
		Description description = new Description();
		description = serviceDescription.findOne(Long.parseLong("16"));
		billConduct.setTypeexpenses(typeEx);
		Item itemConduct = new Item();
		ItemId idItem = new ItemId(serviceBill.getNextSeriesInvoiceId(), (Long.parseLong("1")));
		itemConduct.setIdItem(idItem);
		itemConduct.setDescription(description);
		itemConduct.setQte((Double.parseDouble("1")));
		itemConduct.setPrice(sub_total);
		itemConduct.setTotal(sub_total);
		ServicesO service = new ServicesO();
		service = serviceOffered.findOne((Long.parseLong("8")));
		itemConduct.setServices(service);
		Supplier supplier = new Supplier();
		supplier = serviceSupplier.findOne((Long.parseLong("12")));
		itemConduct.setSupplier(supplier);
		serviceItem.save(id, (Long.parseLong("1")), description, (Double.parseDouble("1")), sub_total, sub_total, sub_total, service, supplier);
		List<Item> items =  new ArrayList<Item>() ;
		items.add(itemConduct);
		billConduct.setItems(items);
		serviceBill.save(billConduct);
		return billConduct;
		
	}

	private Bill InvoiceElectricityMonthly() {
		Bill billElectricity = new Bill();
		Long id = serviceBill.getNextSeriesInvoiceId();
		billElectricity.setInvoiceId(id);
        LocalDate date = LocalDate.now(); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String strDate = formatter.format(date);  
        billElectricity.setDate(strDate);
        Customer customer = new Customer();
        customer = serviceCustomer.findOne((Long.parseLong("15")));
        billElectricity.setCustomer(customer);
        Double sub_total = 70.00;
        billElectricity.setSub_total(sub_total);
		Double tps = 0.00;
		billElectricity.setTps(tps);
		Double tvq =  0.00;
		billElectricity.setTvq(tvq );
		billElectricity.setTotal(sub_total);
		TypesOfExpenses typeEx = new TypesOfExpenses();
		typeEx = serviceTypeOfExpenses.findOne((Long.parseLong("3")));
		Description description = new Description();
		description = serviceDescription.findOne(Long.parseLong("17"));
		billElectricity.setTypeexpenses(typeEx);
		Item itemElectricity = new Item();
		ItemId idItem = new ItemId(serviceBill.getNextSeriesInvoiceId(), (Long.parseLong("1")));
		itemElectricity.setIdItem(idItem);
		itemElectricity.setDescription(description);
		itemElectricity.setQte((Double.parseDouble("1")));
		itemElectricity.setPrice(sub_total);
		itemElectricity.setTotal(sub_total);
		ServicesO service = new ServicesO();
		service = serviceOffered.findOne((Long.parseLong("8")));
		itemElectricity.setServices(service);
		Supplier supplier = new Supplier();
		supplier = serviceSupplier.findOne((Long.parseLong("12")));
		itemElectricity.setSupplier(supplier);
		serviceItem.save(id, (Long.parseLong("1")), description, (Double.parseDouble("1")), sub_total, sub_total, sub_total, service, supplier);
		List<Item> items =  new ArrayList<Item>() ;
		items.add(itemElectricity);
		billElectricity.setItems(items);
		serviceBill.save(billElectricity);
		return billElectricity;
		
	}
	private Bill InvoiceVacationMonthly() {
		Bill billHoliday = new Bill();
		Long id = serviceBill.getNextSeriesInvoiceId();
		billHoliday.setInvoiceId(id);
        LocalDate date = LocalDate.now(); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String strDate = formatter.format(date);  
        billHoliday.setDate(strDate);
        Customer customer = new Customer();
        customer = serviceCustomer.findOne((Long.parseLong("15")));
        billHoliday.setCustomer(customer);
        Double sub_total = 250.00;
        billHoliday.setSub_total(sub_total);
		Double tps = 0.00;
		billHoliday.setTps(tps);
		Double tvq =  0.00;
		billHoliday.setTvq(tvq );
		billHoliday.setTotal(sub_total);
		TypesOfExpenses typeEx = new TypesOfExpenses();
		typeEx = serviceTypeOfExpenses.findOne((Long.parseLong("3")));
		Description description = new Description();
		description = serviceDescription.findOne(Long.parseLong("18"));
		billHoliday.setTypeexpenses(typeEx);
		Item itemHoliday = new Item();
		ItemId idItem = new ItemId(serviceBill.getNextSeriesInvoiceId(), (Long.parseLong("1")));
		itemHoliday.setIdItem(idItem);
		itemHoliday.setDescription(description);
		itemHoliday.setQte((Double.parseDouble("1")));
		itemHoliday.setPrice(sub_total);
		itemHoliday.setTotal(sub_total);
		ServicesO service = new ServicesO();
		service = serviceOffered.findOne((Long.parseLong("8")));
		itemHoliday.setServices(service);
		Supplier supplier = new Supplier();
		supplier = serviceSupplier.findOne((Long.parseLong("12")));
		itemHoliday.setSupplier(supplier);
		serviceItem.save(id, (Long.parseLong("1")), description, (Double.parseDouble("1")), sub_total, sub_total, sub_total, service, supplier);
		List<Item> items =  new ArrayList<Item>() ;
		items.add(itemHoliday);
		billHoliday.setItems(items);
		serviceBill.save(billHoliday);
		return billHoliday;
		
	}
	
	private Bill InvoiceVetementsMonthly() {
		Bill billVetements = new Bill();
		Long id = serviceBill.getNextSeriesInvoiceId();
		billVetements.setInvoiceId(id);
        LocalDate date = LocalDate.now(); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String strDate = formatter.format(date);  
        billVetements.setDate(strDate);
        Customer customer = new Customer();
        customer = serviceCustomer.findOne((Long.parseLong("15")));
        billVetements.setCustomer(customer);
        Double sub_total = 50.00;
        billVetements.setSub_total(sub_total);
		Double tps = 0.00;
		billVetements.setTps(tps);
		Double tvq =  0.00;
		billVetements.setTvq(tvq );
		billVetements.setTotal(sub_total);
		TypesOfExpenses typeEx = new TypesOfExpenses();
		typeEx = serviceTypeOfExpenses.findOne((Long.parseLong("68")));
		Description description = new Description();
		description = serviceDescription.findOne(Long.parseLong("19"));
		billVetements.setTypeexpenses(typeEx);
		Item itemVetements = new Item();
		ItemId idItem = new ItemId(serviceBill.getNextSeriesInvoiceId(), (Long.parseLong("1")));
		itemVetements.setIdItem(idItem);
		itemVetements.setDescription(description);
		itemVetements.setQte((Double.parseDouble("1")));
		itemVetements.setPrice(sub_total);
		itemVetements.setTotal(sub_total);
		ServicesO service = new ServicesO();
		service = serviceOffered.findOne((Long.parseLong("14")));
		itemVetements.setServices(service);
		Supplier supplier = new Supplier();
		supplier = serviceSupplier.findOne((Long.parseLong("28")));
		itemVetements.setSupplier(supplier);
		serviceItem.save(id, (Long.parseLong("1")), description, (Double.parseDouble("1")), sub_total, sub_total, sub_total, service, supplier);
		List<Item> items =  new ArrayList<Item>() ;
		items.add(itemVetements);
		billVetements.setItems(items);
		serviceBill.save(billVetements);
		return billVetements;
		
	}
	
	private Bill InvoiceLoisirMonthly() {
		Bill billLoisir = new Bill();
		Long id = serviceBill.getNextSeriesInvoiceId();
		billLoisir.setInvoiceId(id);
        LocalDate date = LocalDate.now(); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String strDate = formatter.format(date);  
        billLoisir.setDate(strDate);
        Customer customer = new Customer();
        customer = serviceCustomer.findOne((Long.parseLong("15")));
        billLoisir.setCustomer(customer);
        Double sub_total = 100.00;
        billLoisir.setSub_total(sub_total);
		Double tps = 0.00;
		billLoisir.setTps(tps);
		Double tvq =  0.00;
		billLoisir.setTvq(tvq );
		billLoisir.setTotal(sub_total);
		TypesOfExpenses typeEx = new TypesOfExpenses();
		typeEx = serviceTypeOfExpenses.findOne((Long.parseLong("70")));
		Description description = new Description();
		description = serviceDescription.findOne(Long.parseLong("20"));
		billLoisir.setTypeexpenses(typeEx);
		Item itemLoisir = new Item();
		ItemId idItem = new ItemId(serviceBill.getNextSeriesInvoiceId(), (Long.parseLong("1")));
		itemLoisir.setIdItem(idItem);
		itemLoisir.setDescription(description);
		itemLoisir.setQte((Double.parseDouble("1")));
		itemLoisir.setPrice(sub_total);
		itemLoisir.setTotal(sub_total);
		ServicesO service = new ServicesO();
		service = serviceOffered.findOne((Long.parseLong("8")));
		itemLoisir.setServices(service);
		Supplier supplier = new Supplier();
		supplier = serviceSupplier.findOne((Long.parseLong("28")));
		itemLoisir.setSupplier(supplier);
		serviceItem.save(id, (Long.parseLong("1")), description, (Double.parseDouble("1")), sub_total, sub_total, sub_total, service, supplier);
		List<Item> items =  new ArrayList<Item>() ;
		items.add(itemLoisir);
		billLoisir.setItems(items);
		serviceBill.save(billLoisir);
		return billLoisir;
		
	}
	
	private Bill InvoiceUrgencesMonthly() {
		Bill billUrgences = new Bill();
		Long id = serviceBill.getNextSeriesInvoiceId();
		billUrgences.setInvoiceId(id);
        LocalDate date = LocalDate.now(); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String strDate = formatter.format(date);  
        billUrgences.setDate(strDate);
        Customer customer = new Customer();
        customer = serviceCustomer.findOne((Long.parseLong("15")));
        billUrgences.setCustomer(customer);
        Double sub_total = 50.00;
        billUrgences.setSub_total(sub_total);
		Double tps = 0.00;
		billUrgences.setTps(tps);
		Double tvq =  0.00;
		billUrgences.setTvq(tvq );
		billUrgences.setTotal(sub_total);
		TypesOfExpenses typeEx = new TypesOfExpenses();
		typeEx = serviceTypeOfExpenses.findOne((Long.parseLong("70")));
		Description description = new Description();
		description = serviceDescription.findOne(Long.parseLong("21"));
		billUrgences.setTypeexpenses(typeEx);
		Item itemUrgences = new Item();
		ItemId idItem = new ItemId(serviceBill.getNextSeriesInvoiceId(), (Long.parseLong("1")));
		itemUrgences.setIdItem(idItem);
		itemUrgences.setDescription(description);
		itemUrgences.setQte((Double.parseDouble("1")));
		itemUrgences.setPrice(sub_total);
		itemUrgences.setTotal(sub_total);
		ServicesO service = new ServicesO();
		service = serviceOffered.findOne((Long.parseLong("8")));
		itemUrgences.setServices(service);
		Supplier supplier = new Supplier();
		supplier = serviceSupplier.findOne((Long.parseLong("28")));
		itemUrgences.setSupplier(supplier);
		serviceItem.save(id, (Long.parseLong("1")), description, (Double.parseDouble("1")), sub_total, sub_total, sub_total, service, supplier);
		List<Item> items =  new ArrayList<Item>() ;
		items.add(itemUrgences);
		billUrgences.setItems(items);
		serviceBill.save(billUrgences);
		return billUrgences;
		
	}
	private Bill InvoiceGyMMonthly() {
		Bill billGyM = new Bill();
		Long id = serviceBill.getNextSeriesInvoiceId();
		billGyM.setInvoiceId(id);
        LocalDate date = LocalDate.now(); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String strDate = formatter.format(date);  
        billGyM.setDate(strDate);
        Customer customer = new Customer();
        customer = serviceCustomer.findOne((Long.parseLong("15")));
        billGyM.setCustomer(customer);
        Double sub_total = 22.00;
        billGyM.setSub_total(sub_total);
		Double tps = 0.00;
		billGyM.setTps(tps);
		Double tvq =  0.00;
		billGyM.setTvq(tvq );
		billGyM.setTotal(sub_total);
		TypesOfExpenses typeEx = new TypesOfExpenses();
		typeEx = serviceTypeOfExpenses.findOne((Long.parseLong("34")));
		Description description = new Description();
		description = serviceDescription.findOne(Long.parseLong("20"));
		billGyM.setTypeexpenses(typeEx);
		Item itemGyM = new Item();
		ItemId idItem = new ItemId(serviceBill.getNextSeriesInvoiceId(), (Long.parseLong("1")));
		itemGyM.setIdItem(idItem);
		itemGyM.setDescription(description);
		itemGyM.setQte((Double.parseDouble("1")));
		itemGyM.setPrice(sub_total);
		itemGyM.setTotal(sub_total);
		ServicesO service = new ServicesO();
		service = serviceOffered.findOne((Long.parseLong("8")));
		itemGyM.setServices(service);
		Supplier supplier = new Supplier();
		supplier = serviceSupplier.findOne((Long.parseLong("16")));
		itemGyM.setSupplier(supplier);
		serviceItem.save(id, (Long.parseLong("1")), description, (Double.parseDouble("1")), sub_total, sub_total, sub_total, service, supplier);
		List<Item> items =  new ArrayList<Item>() ;
		items.add(itemGyM);
		billGyM.setItems(items);
		serviceBill.save(billGyM);
		return billGyM;
		
	}
}
