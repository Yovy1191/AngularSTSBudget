package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TypesOfExpenses;


@Repository
public interface TypeOfExpensesRepository extends JpaRepository<TypesOfExpenses, Long> {
	
	

}
