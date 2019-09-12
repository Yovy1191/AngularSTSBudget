package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private CustomerRepository repo;

	@Override
	public List<Customer> listAll() {
		return repo.findAll();
	}

	@Override
	public void save(Customer customer) {
		repo.save(customer);
	}

	@Override
	public Customer findOne(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public void delete(Long id) {
		repo.deleteById(id);

	}

}
