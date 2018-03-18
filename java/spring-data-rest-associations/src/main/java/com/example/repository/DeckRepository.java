package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Deck;

public interface DeckRepository extends JpaRepository<Deck, Long> {
}
