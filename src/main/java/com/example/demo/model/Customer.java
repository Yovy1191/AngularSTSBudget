package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "CUSTOMER")
public class Customer {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "idCustomer")
	public long idCustomer;
	
	@JoinColumn(name = "firstName")
	private String firstName;
	
	@JoinColumn(name = "lastName")
	private String lastName;
	

	public Customer() {
	}

	// Getter - Setter 	
	public long getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(long idCustomer) {
		this.idCustomer = idCustomer;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
