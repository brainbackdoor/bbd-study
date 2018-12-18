package com.brainbackdoor.playground.ioc1_4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConifg {
    @Bean
    public BookRepository bookRepository() {
        return new BookRepository();
    }

//    @Bean
//    public BookService bookService() {
//        BookService bookService = new BookService();
//        bookService.setBookRepository(bookRepository());
//        return bookService;
//    }

    @Bean
    public BookService bookService() {
        return new BookService();
    }
}
