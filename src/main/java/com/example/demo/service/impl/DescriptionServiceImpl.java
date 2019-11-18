package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.model.Description;
import com.example.demo.repository.DescriptionRepository;
import com.example.demo.service.IDescriptionService;


@Service
public class DescriptionServiceImpl implements IDescriptionService {

	@Autowired
	private DescriptionRepository repositoryDescription;
	

	@Override
	public List<Description> listAll() {
		return repositoryDescription.findAll();
	}

	@Override
	public void save(Description category) {
		repositoryDescription.save(category);
	}

	@Override
	public Description findOne(Long id) {
		return repositoryDescription.findById(id).get();
	}

	@Override
	public void delete(Long id) {
		repositoryDescription.deleteById(id);

	}

	@Override
	public Page<Description> findAll(Pageable pageable) {
		return repositoryDescription.findAll(pageable);
	}



}
