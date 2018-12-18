package com.brainbackdoor.playgroundspring4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
public class PlaygroundSpring4Application {

    public static void main(String[] args) {
        SpringApplication.run(PlaygroundSpring4Application.class, args);
    }

    /*
        property 변경후 build하면
        running 중에도 데이터가 바뀌는 것을 확인할 수 있다.
     */

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(3);
        return messageSource;
    }
}

