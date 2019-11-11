package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Bill;
import com.example.demo.repository.BillRepository;
import com.example.demo.service.IBillService;

@Service
public class BillServiceImpl implements IBillService {

	@Autowired
	private BillRepository repositoryBill;
	
	
	
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
		return repositoryBill.getExpensesMonthly(monthly);
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
