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
@Table(name = "PROPERTY")
public class Property {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProperty")
	private Long idProperty;
	
	@Column(name = "value")
	private Double value;
			
	@OneToOne
	@JoinColumn(name = "propertyTypeId")
	public TypeProperty propertyType;
	
	
	@OneToOne
	@JoinColumn(name = "propertyId")
	public Customer customer;
	
	
	// Getter  - Setter
	
	public Long getIdProperty() {
		return idProperty;
	}



	public void setIdProperty(Long idProperty) {
		this.idProperty = idProperty;
	}

	
	
	public Double getValue() {
		return value;
	}



	public void setValue(Double value) {
		this.value = value;
	}



	public TypeProperty getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(TypeProperty propertyType) {
		this.propertyType = propertyType;
	}
	
	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


}
