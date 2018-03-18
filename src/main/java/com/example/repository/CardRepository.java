package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.model.Card;

@RepositoryRestResource(collectionResourceRel = "card")
public interface CardRepository extends JpaRepository<Card, Long> {

}
