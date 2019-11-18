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
@Table(name = "TYPEPROPERTY")
public class TypeProperty {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "idtypeproperty")
	private Long idtypeproperty;
	
	@NotNull
	@Size(min=2, max=40, message="First name must be between 2 and 40 characters")
	@JoinColumn(name = "nameProperty")
	private String nameProperty;



	public Long getIdtypeproperty() {
		return idtypeproperty;
	}

	public void setIdtypeproperty(Long idtypeproperty) {
		this.idtypeproperty = idtypeproperty;
	}

	public String getNameProperty() {
		return nameProperty;
	}

	public void setNameProperty(String nameProperty) {
		this.nameProperty = nameProperty;
	}

	public TypeProperty() {
	}

	public TypeProperty(Long idtypeproperty,
			@NotNull @Size(min = 2, max = 40, message = "First name must be between 2 and 40 characters") String nameProperty) {
		super();
		this.idtypeproperty = idtypeproperty;
		this.nameProperty = nameProperty;
	}
	
	
	
	

}
