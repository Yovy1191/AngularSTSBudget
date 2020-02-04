package com.example.demo.Controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Description;
import com.example.demo.model.Item;
import com.example.demo.model.ItemId;
import com.example.demo.model.ItemWrapper;
import com.example.demo.model.Pager;
import com.example.demo.model.ServicesO;
import com.example.demo.model.Supplier;
import com.example.demo.service.IDescriptionService;
import com.example.demo.service.IItemService;
import com.example.demo.service.IServicesOffered;
import com.example.demo.service.ISupplierService;


@Controller
public class ItemController {
	
	 private static final int BUTTONS_TO_SHOW = 3;
	 private static final int INITIAL_PAGE = 0;
	 private static final int INITIAL_PAGE_SIZE = 5;
	 private static final int[] PAGE_SIZES = { 5, 10};

	@Autowired
	private IItemService serviceItem;
	
	@Autowired
	private IServicesOffered serviceOffered;
	
	@Autowired
	private ISupplierService serviceSupplier;
	
	@Autowired
	private IDescriptionService serviceDescription;
	
		
	@RequestMapping("/item")
	private String ListItems(Model model) {
		model.addAttribute("listitems", serviceItem.findAllByOrderByDateAsc());
		return "item";
	}
	
	@GetMapping("/item")
	 public ModelAndView ShowCategoryPage(@RequestParam("pageSize") Optional<Integer> pageSize,
	            @RequestParam("page") Optional<Integer> page){
	        ModelAndView modelAndView = new ModelAndView("/item");
	        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
	        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
	        Page<Item> listitems = serviceItem.findAll(PageRequest.of(evalPage, evalPageSize));
	        Pager pager = new Pager(listitems.getTotalPages(),listitems.getNumber(),BUTTONS_TO_SHOW);
	        modelAndView.addObject("listitems",listitems);
	        modelAndView.addObject("selectedPageSize", evalPageSize);
	        modelAndView.addObject("pageSizes", PAGE_SIZES);
	        modelAndView.addObject("pager", pager);
	        return modelAndView;
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
		ServicesO service = serviceOffered.findOne(Long.parseLong(serviceid));
		String supplierid =  request.getParameter("idSupplier");
		Supplier supplier = serviceSupplier.findOne(Long.parseLong(supplierid));
	    item.setSupplier(supplier);
        item.setServices(service);
        Item itemsave = serviceItem.save(item.getInvoiceId(),item.getItemId(), item.getDate(),  item.getQte(), item.getPrice(), item.getSubtotal(),item.getServices(), item.getSupplier(),
        								  item.getTotal(), item.getDescription());
		return "item" + itemsave.getIdItem() + "item"   ;
	}
	
	@RequestMapping(value = "/edititem/{InvoiceId}/edititem/{ItemId}")
	public String ShowEditItem(Model model, @PathVariable Long InvoiceId,@PathVariable Long ItemId) {
		ItemId idItem = new ItemId(InvoiceId,ItemId);
		model.addAttribute("item", serviceItem.getBypk(idItem));
		return "edititem";
	}

	@RequestMapping(value = "edititem", method = RequestMethod.POST)
	public String saveEditItem(Model model, Item item, Description description, HttpServletRequest request) {
		String descriptionId = request.getParameter("descriptionId");
		description = serviceDescription.findOne(Long.parseLong(descriptionId));
		item.setDescription(description);
		String qte = request.getParameter("qte");
		item.setDescription(description);
		item.setQte(Double.parseDouble(qte));
		serviceItem.saveEdit(item);
		return "redirect:/item ";
	}

//	@RequestMapping(value = "/deletecustomer/{idCustomer}")
//	public String deleteCustomer(Model model, @PathVariable Long idCustomer) {
//		model.addAttribute("customer", serviceCustomer.findOne(idCustomer));
//		serviceCustomer.delete(idCustomer);
//		return "redirect:/customer";
//	}

}
