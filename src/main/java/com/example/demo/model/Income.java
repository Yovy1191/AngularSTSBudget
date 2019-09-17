package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "INCOME")
public class Income {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idIncome")
	private Long idIncome;
	
	@JoinColumn(name = "date")
	private String date;
	
	@JoinColumn(name = "value")
	private Double value;
		
	@OneToOne
	@JoinColumn(name = "typeIncomeId")
	public TypeIncome typeIncome;

	
	@OneToOne
	@JoinColumn(name = "customer")
	public Customer customer;
	
	
	public Income() {
	
	}

	public Long getIdIncome() {
		return idIncome;
	}

	public void setIdIncome(Long idIncome) {
		this.idIncome = idIncome;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public TypeIncome getTypeIncome() {
		return typeIncome;
	}

	public void setTypeIncome(TypeIncome typeIncome) {
		this.typeIncome = typeIncome;
	}



	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	

}
