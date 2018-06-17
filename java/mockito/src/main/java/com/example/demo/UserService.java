package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private NameService nameService;
    private User user;

    @Autowired
    public UserService(NameService nameService) {
        this.nameService = nameService;
    }

    public String getUserName(String id) {
        return nameService.getName(id);
    }

    public User getUser(String id) {
        return user;
    }
}
