package com.example.demo.Controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.TypesOfExpenses;
import com.example.demo.service.ITypeOfExpensesService;

@Controller
public class TypeOfExpensesController {
	

	
	@Autowired
	private ITypeOfExpensesService serviceTypeOfExpenses;
	
		
	@RequestMapping("/typeExpenses")
	private String ListTypesOfExpenses(Model model) {
		model.addAttribute("listOfExpenses", serviceTypeOfExpenses.listAll());
		return "typeExpenses";
	}
	
	
	@RequestMapping(value = "/addtypexpenses", method = RequestMethod.POST)
	public String SaveTypeOfExpenses(Model model, TypesOfExpenses expense, HttpServletRequest request,
			@RequestParam("idExpense") Long idExpense, @RequestParam("nameTypeExpense") String nameTypeExpense) {
		expense.setIdExpense(idExpense);
		expense.setNameTypeExpense(nameTypeExpense);
		serviceTypeOfExpenses.save(expense);
		return "redirect:/addtypexpenses";
	}
	
	
//	@RequestMapping(value = "/additem/saveaction", method = RequestMethod.POST)
//	@ResponseBody
//	public String SubmitForm(Model model, ItemWrapper item,  HttpSession session,HttpServletRequest request) {
//		String serviceid =  request.getParameter("idService");
//		ServicesOffered service = serviceOffered.findOne(Long.parseLong(serviceid));
//		String supplierid =  request.getParameter("idSupplier");
//		Supplier supplier = serviceSupplier.findOne(Long.parseLong(supplierid));
//	    item.setSupplier(supplier);
//        item.setServices(service);
//        Item itemsave = serviceItem.save(item.getInvoiceId(),item.getItemId(), item.getDescription(),
//        								  item.getQte(), item.getPrice(),item.getTotal(), item.getServices(), item.getSupplier());
//		return "item" + itemsave.getIdItem() + "item"   ;
//	}
}
