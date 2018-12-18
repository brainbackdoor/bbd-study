package com.brainbackdoor.playgroundspring2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    List<BookRepository> bookRepositories;

    public void printBookRepository() {
        this.bookRepositories.forEach(System.out::println);
    }
}
