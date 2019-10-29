package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TYPEINCOME")
public class TypeIncome {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "idincome")
	private Long idincome;
	
	@NotNull
	@Size(min=2, max=40, message="First name must be between 2 and 40 characters")
	@JoinColumn(name = "nameIncome")
	private String nameIncome;
	
   
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
	
	public TypeIncome() {

	}



}
