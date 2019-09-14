package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "TYPEINCOME")
public class TypeIncome {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idincome")
	private Long idincome;
	
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
