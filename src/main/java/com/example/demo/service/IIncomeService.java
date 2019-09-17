package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Income;

public interface IIncomeService {
	
	public List<Income> listAll();
	Income save(Income income); 
	public Optional<Income> findOne(Long id);
	public void delete(Long id);
	

}