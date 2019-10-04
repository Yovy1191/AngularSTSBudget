package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ServicesOffered;





@Repository
public interface ServicesOfferedRepository extends PagingAndSortingRepository<ServicesOffered, Long>  {

}
