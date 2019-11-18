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
@Table(name = "DESCRIPTION")
public class Description {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JoinColumn(name = "descriptionId")
	public long descriptionId;
	
	@NotNull
	@Size(min=2, max=40, message="First name must be between 2 and 40 characters")
	@JoinColumn(name = "descriptionName")
	private String descriptionName;

	
	public long getDescriptionId() {
		return descriptionId;
	}

	public void setDescriptionId(long descriptionId) {
		this.descriptionId = descriptionId;
	}

	public String getDescriptionName() {
		return descriptionName;
	}

	public void setDescriptionName(String descriptionName) {
		this.descriptionName = descriptionName;
	}

		
	public Description(long descriptionId,
			@NotNull @Size(min = 2, max = 40, message = "First name must be between 2 and 40 characters") String descriptionName) {
		super();
		this.descriptionId = descriptionId;
		this.descriptionName = descriptionName;
	}

	public Description() {
		
	}
	
	

}
