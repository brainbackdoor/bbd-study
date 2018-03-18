package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.model.Card;
import com.example.model.Deck;
import com.example.repository.CardRepository;
import com.example.repository.DeckRepository;

@Component
public class Initializer implements ApplicationRunner {

	private DeckRepository deckRepository;

	private CardRepository cardRepository;

	@Autowired
	public Initializer(DeckRepository deckRepository, CardRepository cardRepository) {
		this.deckRepository = deckRepository;
		this.cardRepository = cardRepository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		//http://localhost:8080/api/decks/1
		Deck deck = new Deck();
		deck.setName("DeckTemp");
		deckRepository.save(deck);

		//Use PostMan (https://www.getpostman.com/)
		//POST http://localhost:8080/api/decks/1/cards
		//Headers Content-Type : text/uri-list
		//Body (text) : http://localhost:8080/api/cards/1 
		
		//http://localhost:8080/api/decks/1/cards
		Card card = new Card();
		card.setName("BBD");
		card.setContent("CodeSquad");
		cardRepository.save(card);
	}
}