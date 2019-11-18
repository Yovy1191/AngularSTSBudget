package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.ServicesO;
import com.example.demo.repository.ServicesOfferedRepository;
import com.example.demo.service.IServicesOffered;

@Service
public class ServicesOfferedImpl implements IServicesOffered {

	@Autowired
	private ServicesOfferedRepository repositoryServicesOffered;
	
	
	@Override
	public List<ServicesO> listAll() {
		return (List<ServicesO>) repositoryServicesOffered.findAll();
	}

	@Override
	public void save(ServicesO servicesoffered) {
			repositoryServicesOffered.save(servicesoffered);
	}

	@Override
	public ServicesO findOne(long id) {
		return repositoryServicesOffered.findById(id).get();
	}

	@Override
	public void delete(long id) {
		repositoryServicesOffered.deleteById(id);
		
	}

	@Override
	public Page<ServicesO> listAll(Pageable pageable) {
		return repositoryServicesOffered.findAll(pageable);
	}

}
