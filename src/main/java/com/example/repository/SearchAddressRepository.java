package com.example.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.example.model.SearchAddress;


public interface SearchAddressRepository extends Repository<SearchAddress, Long> {

	SearchAddress findByValue(String value);
	SearchAddress findByKey(long key);
	List<SearchAddress> findByValueContaining(String value);
	List<SearchAddress> findAll();
}
