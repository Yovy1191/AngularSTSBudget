package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.TypeIncome;


public interface ITypeIncomeService {
	
	TypeIncome save(TypeIncome TypeIncome); 
	public void deleteById(Long id);
	public TypeIncome findOne(Long id);
	Page<TypeIncome> listAll(Pageable pageable);
	List<TypeIncome> findAll();



}
