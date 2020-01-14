package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Bill;
import com.example.demo.model.Item;
import com.example.demo.model.ItemWrapper;
import com.example.demo.repository.BillRepository;
import com.example.demo.service.IBillService;
import com.example.demo.service.IItemService;

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
		List<Item> items =  serviceItem.listAll();
		ItemWrapper itemw = new ItemWrapper();
		
		
		for (Bill b:billMonthy) 
        { 
            for (Item i:items) 
            { 
               if (b.invoiceId.equals(i.getIdItem().getInvoiceId()))	
               		{ 
            	    List<Item> itemCopy = new ArrayList<Item>();
              		itemw.setItemId(i.getIdItem().getItemId());
              		itemw.setInvoiceId(i.getIdItem().getInvoiceId());
               		itemw.setSupplier(i.supplier);
               		itemw.setServices(i.services);
               		itemw.setDescription(i.getDescription());
               		itemw.setPrice(i.getPrice());
               		itemw.setTotal(i.getTotal());
               		Item itemsave = serviceItem.save(itemw.getInvoiceId(), itemw.getItemId(), itemw.getDescription(),
        				itemw.getQte(), itemw.getPrice(), itemw.getSubtotal(), itemw.getTotal(), itemw.getServices(), itemw.getSupplier());
               		itemCopy.add(itemsave);
               	    b.setItems(itemCopy); 
               		} 
           
               
           	} 
        
         
        } 
		
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
		return repositoryBill.getExpensesBiannual();
	}

	@Override
	public List<Bill> getExpensesQuartely() {
		return repositoryBill.getExpensesQuartely();
	}

	@Override
	public Page<Bill> listAll(Pageable pageable) {
		return repositoryBill.findAll(pageable);
	}
	


}
