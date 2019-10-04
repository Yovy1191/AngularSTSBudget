package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Periodicity;
import com.example.demo.repository.PeriodicityRepository;
import com.example.demo.service.IPeriodicityService;

@Service
public class PeriodicityImpl implements IPeriodicityService {

	@Autowired
	private PeriodicityRepository repositoryPeriodicity;
	
	
	@Override
	public List<Periodicity> listAll() {
	  return repositoryPeriodicity.findAll();
	}

	@Override
	public Periodicity save(Periodicity periodicity) {
		return repositoryPeriodicity.save(periodicity);
	}

	@Override
	public Periodicity findOne(Long id) {
		return repositoryPeriodicity.findById(id).get();
	}

	@Override
	public void delete(Long id) {

		repositoryPeriodicity.deleteById(id);
		
	}

	

	
	

}
