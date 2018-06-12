package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class NameService {
    public String getName(String id) {
        return "Real user name";
    }
}
