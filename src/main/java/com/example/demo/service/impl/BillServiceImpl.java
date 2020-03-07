package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Bill;
import com.example.demo.model.Item;
import com.example.demo.model.ItemWrapper;
import com.example.demo.repository.BillRepository;
import com.example.demo.service.IBillService;
import com.example.demo.service.IItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements IBillService {

	@Autowired
	private BillRepository repositoryBill;
	
	@Autowired
	private IItemService serviceItem;
	

	
	@Override
	public Bill save(Bill bill) {
		return repositoryBill.save(bill);

	}

	@Override
	public Bill findOne(Long id) {
		return repositoryBill.findById(id).get();
	}

	@Override
	public void delete(Long id) {
		repositoryBill.deleteById(id);

	}

	@Override
	public Long getNextSeriesInvoiceId() {
		return repositoryBill.getNextSeriesInvoiceId();
	}

	@Override
	public List<Bill> getExpensesMonthly(Long monthly) {
		List<Bill> billMonthy = repositoryBill.getBillMonthly(monthly);
		TotalExpenses(billMonthy); 
	return billMonthy;
	}


	@Override
	public Double TotalInvoiceBudget(List<Bill> invoiceTotal) {
	int total = 0;	
	for (Bill item : invoiceTotal) {
		    total = total + item.getTotal().intValue();	}
	return  Double.valueOf(total);
	}

	@Override
	public List<Bill> findAll() {
		return repositoryBill.findAll();
	}

	@Override
	public List<Bill> getExpensesBiannual() {
		{
			List<Bill> billbudget = repositoryBill.getExpensesBiannual();
			TotalExpenses(billbudget); 
		return billbudget;
		}
	
	}

	@Override
	public List<Bill> getExpensesQuartely() {
		List<Bill> billbudget = repositoryBill.getExpensesQuartely();
		TotalExpenses(billbudget); 
		return billbudget;
	}

	@Override
	public Page<Bill> listAll(Pageable pageable) {
		return repositoryBill.findAll(pageable);
	}
	
	private List<Bill> TotalExpenses(List<Bill> billbudget) {
		List<Item> items =  serviceItem.listAll();
		ItemWrapper itemw = new ItemWrapper();
				
		
		for (Bill b:billbudget) 
		{ 
		    for (Item i:items) 
		    { 
		       if (b.invoiceId.equals(i.getIdItem().getInvoiceId()))	
		       		{ 
		    	    List<Item> itemCopy = new ArrayList<Item>();
		      		itemw.setItemId(i.getIdItem().getItemId());
		      		itemw.setQte(i.getQte());
		      		itemw.setInvoiceId(i.getIdItem().getInvoiceId());
		      		itemw.setDate(i.getDate());
		       		itemw.setSupplier(i.supplier);
		       		itemw.setServices(i.services);
		       		itemw.setDescription(i.getDescription());
		       		itemw.setPrice(i.getPrice());
		       		itemw.setTotal(i.getTotal());
		       		Item itemsave = serviceItem.save(itemw.getInvoiceId(),  itemw.getItemId(), itemw.getDate(), itemw.getQte(), itemw.getPrice(), itemw.getSubtotal(), 
						 itemw.getServices(), itemw.getSupplier(),itemw.getTotal(), itemw.getDescription());
		       		itemCopy.add(itemsave);
		       	    b.setItems(itemCopy); 
		       		} 
		   
		       
		   	} 
		
		 
		}
		
	//	 getExpenses();
		return billbudget;
	}

	
	

	






}
