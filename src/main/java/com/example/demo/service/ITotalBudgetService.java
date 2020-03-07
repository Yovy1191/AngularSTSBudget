package com.example.demo.service;

import java.util.List;

import com.example.demo.model.TotalBudget;

public interface ITotalBudgetService {

	public List<TotalBudget> listAll();
	public void save(TotalBudget totalbudget); 
	public TotalBudget findOne(Long id);
	public void delete(Long id);

}
