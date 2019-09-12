package com.example.demo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "INCOME")
public class Income {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idincome")
	private Long idincome;
	
	@JoinColumn(name = "nameIncome")
	private String nameIncome;
	
    @OneToMany(mappedBy = "income", cascade = CascadeType.ALL)
	private Set<Customer> customers;

	public Long getIdincome() {
		return idincome;
	}

	public void setIdincome(Long idincome) {
		this.idincome = idincome;
	}

	public String getNameIncome() {
		return nameIncome;
	}

	public void setNameIncome(String nameIncome) {
		this.nameIncome = nameIncome;
	}

	
	public Income() {

	}



}
