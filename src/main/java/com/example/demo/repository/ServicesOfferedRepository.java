package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ServicesO;

@Repository
public interface ServicesOfferedRepository extends PagingAndSortingRepository<ServicesO, Long>  {

}
