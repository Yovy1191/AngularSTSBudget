package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.ServicesO;



public interface IServicesOffered {
	
	Page<ServicesO> listAll(Pageable pageable);
	List<ServicesO> listAll();
	public void save(ServicesO servicesoffered); 
	public ServicesO findOne(long id);
	public void delete(long id);

}
