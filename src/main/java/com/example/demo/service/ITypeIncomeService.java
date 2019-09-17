package com.example.demo.service;

import java.util.List;

import com.example.demo.model.TypeIncome;


public interface ITypeIncomeService {
	
	TypeIncome save(TypeIncome TypeIncome); 
	public void deleteById(Long id);
	public TypeIncome findOne(Long id);
	public void delete(Long id);
	public List<TypeIncome> listAll();



}
