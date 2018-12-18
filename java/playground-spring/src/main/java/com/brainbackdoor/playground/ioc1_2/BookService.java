package com.brainbackdoor.playground.ioc1_2;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;

@Service
public class BookService {

    public BookRepository bookRepository;

    public BookService() {

    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book save(Book book) {
        book.setCreated(new Date());
        book.setStatus(BookStatus.DRAFT);
        return bookRepository.save(book);
    }

}
