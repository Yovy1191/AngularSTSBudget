package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Customer;

public interface ICustomerService {

	public List<Customer> listAll();
	public void save(Customer customer); 
	public Customer findOne(Long id);
	public void delete(Long id);

}
