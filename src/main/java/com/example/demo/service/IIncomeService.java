package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Income;


public interface IIncomeService {
	
	public List<Income> listAll();
	public void save(Income income); 
	public Income findOne(Long id);
	public void delete(Long id);

}
