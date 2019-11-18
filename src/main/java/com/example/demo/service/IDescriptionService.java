package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.Description;

public interface IDescriptionService {

	Page<Description> findAll(Pageable pageable);
	public List<Description> listAll();
	public void save(Description description); 
	public Description findOne(Long id);
	public void delete(Long id);

}
