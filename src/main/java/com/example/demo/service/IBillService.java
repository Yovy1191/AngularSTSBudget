package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Bill;



public interface IBillService {
	
	public List<Bill> listAll();
	Bill save(Bill bill); 
	public Bill findOne(Long id);
	public void delete(Long id);
	public Long getNextSeriesInvoiceId();

}
