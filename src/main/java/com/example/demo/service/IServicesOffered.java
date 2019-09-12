package com.example.demo.service;

import java.util.List;

import com.example.demo.model.ServicesOffered;


public interface IServicesOffered {
	
	public List<ServicesOffered> listAll();
	public void save(ServicesOffered servicesoffered); 
	public ServicesOffered findOne(Long id);
	public void delete(Long id);

}
