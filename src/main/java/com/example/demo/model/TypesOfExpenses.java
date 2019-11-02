package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TYPEOFEXPENSES")
public class TypesOfExpenses {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idExpense")
	private Long idExpense;
	
	@NotNull
	@Size(min=2, max=100, message="First name must be between 2 and 100 characters")
	@Column(name = "nameTypeExpense")
	private String nameTypeExpense ;
	
	
	@OneToOne
	@JoinColumn(name = "categoryId")
	public Category category;


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
