package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TYPEPROPERTY")
public class TypeProperty {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idTypeProperty")
	private Long idTypeProperty;
	
	private String nameProperty;

	public Long getIdTypeProperty() {
		return idTypeProperty;
	}

	public void setIdTypeProperty(Long idTypeProperty) {
		this.idTypeProperty = idTypeProperty;
	}

	public String getNameProperty() {
		return nameProperty;
	}

	public void setNameProperty(String nameProperty) {
		this.nameProperty = nameProperty;
	}
	
	
	
	

}
