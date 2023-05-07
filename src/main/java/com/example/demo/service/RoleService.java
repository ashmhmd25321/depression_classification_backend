package com.example.demo.service;

import com.example.demo.models.Role;
import com.example.demo.repository.RoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleDAO roleDAO;

    public Role createNewRole(Role role) {
        return roleDAO.save(role);
    }
}
