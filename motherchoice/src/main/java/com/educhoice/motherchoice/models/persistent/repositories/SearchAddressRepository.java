package com.educhoice.motherchoice.models.persistent.repositories;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.educhoice.motherchoice.models.persistent.geolocation.SearchAddress;

public interface SearchAddressRepository extends Repository<SearchAddress, Long> {

	List<SearchAddress> findByValueContaining(String value);

}