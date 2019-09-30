package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "SUPPLIER")
public class Supplier  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idsupplier")
	private Long idsupplier;
	
	@NotNull
	@Size(min=2, max=100, message="First name must be between 2 and 100 characters")
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
	
	@Override
    public String toString() {
        return "Supplier [idsupplier=" + idsupplier + ", nameSupplier=" + nameSupplier +  "]";
    }

	

}
