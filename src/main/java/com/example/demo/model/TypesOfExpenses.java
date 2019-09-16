package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TYPEOFEXPENSES")
public class TypesOfExpenses {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idExpense")
	private Long idExpense;
	
	private String nameTypeExpense ;
	

	public enum HomeExpenses {
	    FORNITURE,
	    HOME_APPLIANCE,

	}
	

	public Long getIdExpense() {
		return idExpense;
	}


	public void setIdExpense(Long idExpense) {
		this.idExpense = idExpense;
	}



	public String getNameTypeExpense() {
		return nameTypeExpense;
	}



	public void setNameTypeExpense(String nameTypeExpense) {
		this.nameTypeExpense = nameTypeExpense;
	}


	
	

}
