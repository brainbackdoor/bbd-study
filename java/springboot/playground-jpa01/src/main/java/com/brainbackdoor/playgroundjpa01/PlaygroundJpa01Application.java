package com.brainbackdoor.playgroundjpa01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class PlaygroundJpa01Application {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(PlaygroundJpa01Application.class, args);
    }

}

