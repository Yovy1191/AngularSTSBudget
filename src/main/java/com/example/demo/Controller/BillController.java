package com.example.demo.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Bill;
import com.example.demo.model.Customer;
import com.example.demo.model.Item;
import com.example.demo.model.ItemWrapper;
import com.example.demo.model.ItemsCreationWrapper;
import com.example.demo.model.ServicesOffered;
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
		model.addAttribute("listbill", serviceBill.listAll(new PageRequest(page,4)));
		return "bill";
	}

	@RequestMapping(value = "/addbill2", method = RequestMethod.POST)
	public String SaveBill(@ModelAttribute ItemsCreationWrapper form, Model model,  ItemWrapper itemw,  HttpServletRequest request) {
		Bill bill = new Bill();
	//	Long itemid = (long) 1 ;
//		
//		if (itemw.getItemId() != null )
//		{
//			itemid = itemw.getItemId() ;
//			itemw.setItemId(itemid++);
//		}
//		else
//		{
//			itemw.setItemId(itemid);
//		}	
		Customer customer = new Customer();
		ServicesOffered service = new ServicesOffered();
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
		Long inextId =  serviceBill.getNextSeriesInvoiceId();
		String itemid = request.getParameter("ItemId");

		System.out.println("helolllllllll" + itemid);
		
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

}
