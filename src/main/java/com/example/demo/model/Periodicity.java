package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PERIODICITY")
public class Periodicity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPeriodicity")
	private Long idPeriodicity;
	
	@Column(name = "namePeriodicity")
	private String namePeriodicity;

	public Long getIdPeriodicity() {
		return idPeriodicity;
	}

	public void setIdPeriodicity(Long idPeriodicity) {
		this.idPeriodicity = idPeriodicity;
	}

	public String getNamePeriodicity() {
		return namePeriodicity;
	}

	public void setNamePeriodicity(String namePeriodicity) {
		this.namePeriodicity = namePeriodicity;
	}

	
	public Periodicity() {}
	
	
	

}
