package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Periodicity;

public interface IPeriodicityService {

	public List<Periodicity> listAll();
	Periodicity save(Periodicity periodicity); 
	public Periodicity findOne(Long id);
	public void delete(Long id);

}
