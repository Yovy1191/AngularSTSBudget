package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ServicesOffered;
import com.example.demo.repository.ServicesOfferedRepository;
import com.example.demo.service.IServicesOffered;

@Service
public class ServicesOfferedImpl implements IServicesOffered {

	@Autowired
	private ServicesOfferedRepository repositoryServicesOffered;
	
	
	@Override
	public List<ServicesOffered> listAll() {
		return repositoryServicesOffered.findAll();
	}

	@Override
	public void save(ServicesOffered servicesoffered) {
			repositoryServicesOffered.save(servicesoffered);
	}

	@Override
	public ServicesOffered findOne(Long id) {
		return repositoryServicesOffered.findById(id).get();
	}

	@Override
	public void delete(Long id) {
		repositoryServicesOffered.deleteById(id);
		
	}

}
