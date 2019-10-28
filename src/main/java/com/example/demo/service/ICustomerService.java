package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.Customer;

public interface ICustomerService {

	Page<Customer> findAll(Pageable pageable);
	public List<Customer> listAll();
	public void save(Customer customer); 
	public Customer findOne(Long id);
	public void delete(Long id);

}
