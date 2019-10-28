package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.Income;

public interface IIncomeService {
	
	Page<Income> listAll(Pageable pageable);
	public List<Income> findAll();
	Income save(Income income); 
	public Optional<Income> findOne(Long id);
	public void delete(Long id);
	public List<Income> getIncomeMonthly(Long monthly);
	public Double TotalIncomeBudget(List<Income> incomeTotal) ; 
	public List<Income> getIncomeBiannual();
	public List<Income> getExpensesQuartely();
	public Long getNextSeriesIncomeId();

}
