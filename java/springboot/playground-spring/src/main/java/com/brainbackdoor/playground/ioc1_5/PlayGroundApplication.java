package com.brainbackdoor.playground.ioc1_5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class PlayGroundApplication {

    /*
        java 파일로 빈 등록 + ComponentScan
     */
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ApplicationConifg.class);

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));
        BookService bookService = (BookService) context.getBean("bookService");
        System.out.println(bookService.bookRepository != null);
    }

}

