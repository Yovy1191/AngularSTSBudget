package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.example.demo.model.Bill;



public interface IBillService {
	
	public List<Bill> listAll(PageRequest pageRequest);
	Bill save(Bill bill); 
	public Bill findOne(Long id);
	public void delete(Long id);
	public Long getNextSeriesInvoiceId();
	List<Bill> getExpensesMonthly(Long monthly);
	public Double TotalInvoiceBudget(List<Bill> invoiceTotal) ; 

}
