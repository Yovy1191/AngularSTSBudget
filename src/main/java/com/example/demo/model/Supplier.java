package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SUPPLIER")
public class Supplier  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idsupplier")
	private Long idsupplier;
	
	@Column(name = "namesupplier")
	private String nameSupplier;
	
	
	public Supplier() {	}



	public Long getIdsupplier() {
		return idsupplier;
	}

	public void setIdsupplier(Long idsupplier) {
		this.idsupplier = idsupplier;
	}


	public String getNameSupplier() {
		return nameSupplier;
	}


	public void setNameSupplier(String nameSupplier) {
		this.nameSupplier = nameSupplier;
	}
	
	

}
