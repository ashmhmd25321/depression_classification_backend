package com.example.demo.service;

import com.example.demo.models.User;
import com.example.demo.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public User registerUser(User user){
        return userDAO.save(user);
    }
}
