package com.example.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.example.model.SearchAddress;

public interface SearchAddressRepository extends Repository<SearchAddress, Long> {

	SearchAddress findByValue(String value);

	SearchAddress findByKey(long key);

	List<SearchAddress> findByValueContaining(String value);

	List<SearchAddress> findAll();
}
