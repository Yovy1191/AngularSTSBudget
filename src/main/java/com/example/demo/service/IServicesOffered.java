package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.ServicesOffered;



public interface IServicesOffered {
	
	Page<ServicesOffered> listAll(Pageable pageable);
	List<ServicesOffered> listAll();
	public void save(ServicesOffered servicesoffered); 
	public ServicesOffered findOne(Long id);
	public void delete(Long id);

}
