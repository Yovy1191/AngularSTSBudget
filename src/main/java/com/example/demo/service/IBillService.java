package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.Bill;




public interface IBillService {
	
	public Page<Bill> listAll(Pageable pageable);
	public  List<Bill> findAll();
	Bill save(Bill bill); 
	public Bill findOne(Long id);
	public void delete(Long id);
	public Long getNextSeriesInvoiceId();
	List<Bill> getExpensesMonthly(Long monthly);
	public Double TotalInvoiceBudget(List<Bill> invoiceTotal); 
	public List<Bill> getExpensesBiannual();
	public List<Bill> getExpensesQuartely();
	
	
}
