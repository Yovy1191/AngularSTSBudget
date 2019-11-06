package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.Overview;

public interface IOverviewService {

	Page<Overview> findAll(Pageable pageable);
	public List<Overview> listAll();
	public void save(Overview overview); 
	public Overview findOne(Long id);
	public void delete(Long id);

}
