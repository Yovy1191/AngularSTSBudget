package com.example.demo.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Item;
import com.example.demo.model.ItemWrapper;
import com.example.demo.model.ServicesOffered;
import com.example.demo.model.Supplier;
import com.example.demo.service.IItemService;
import com.example.demo.service.IServicesOffered;
import com.example.demo.service.ISupplierService;

@Controller
public class ItemController {
	

	@Autowired
	private IItemService serviceItem;
	
	@Autowired
	private IServicesOffered serviceOffered;
	
	@Autowired
	private ISupplierService serviceSupplier;
	
		
	@RequestMapping("/item")
	private String ListItems(Model model) {
		model.addAttribute("listitems", serviceItem.listAll());
		return "item";
	}
	
	
	@RequestMapping(value = "/save")
	public String SaveItem(Model model, HttpSession session,HttpServletRequest request) {
		model.addAttribute("item", new ItemWrapper());
		return "additem";
	}
	
	
	@RequestMapping(value = "/additem/saveaction", method = RequestMethod.POST)
	@ResponseBody
	public String SubmitForm(Model model, ItemWrapper item,  HttpSession session,HttpServletRequest request) {
		String serviceid =  request.getParameter("idService");
		ServicesOffered service = serviceOffered.findOne(Long.parseLong(serviceid));
		String supplierid =  request.getParameter("idSupplier");
		Supplier supplier = serviceSupplier.findOne(Long.parseLong(supplierid));
	    item.setSupplier(supplier);
        item.setServices(service);
        Item itemsave = serviceItem.save(item.getInvoiceId(),item.getItemId(), item.getDescription(),
        								  item.getQte(), item.getPrice(),item.getTotal(), item.getServices(), item.getSupplier());
		return "item" + itemsave.getIdItem() + "item"   ;
	}
}
