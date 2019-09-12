package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Bill;
import com.example.demo.repository.BillRepository;
import com.example.demo.service.IBillService;

@Service
public class BillServiceImpl implements IBillService {

	@Autowired
	private BillRepository repositoryBill;

	@Override
	public List<Bill> listAll() {
		return repositoryBill.findAll();

	}

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

}
