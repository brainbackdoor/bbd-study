package com.example.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.example.model.SearchNickName;

public interface SearchNickNameRepository extends Repository<SearchNickName, Long> {

	List<SearchNickName> findByValueContaining(String value);
}
