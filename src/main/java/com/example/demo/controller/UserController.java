package com.example.demo.controller;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repository.RoleDAO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/registerUser")
    public User registerUser(@RequestBody User user) {

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        Role role = new Role();
        role.setRoleName("USER");
        role.setRoleDescription("Access only user Functions");
        roleDAO.save(role);

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRole(roles);
        return userService.registerUser(user);
    }

    @PostMapping("/registerAdmin")
    public User registerAdmin(@RequestBody User user) {

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        Role role = new Role();
        role.setRoleName("ADMIN");
        role.setRoleDescription("Access All Functions");
        roleDAO.save(role);

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRole(roles);
        return userService.registerUser(user);
    }

    @PostMapping("/registerTaylor")
    public User registerTaylor(@RequestBody User user) {

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        Role role = new Role();
        role.setRoleName("TAYLOR");
        role.setRoleDescription("Access only taylor Functions");
        roleDAO.save(role);

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRole(roles);
        return userService.registerUser(user);
    }

    @GetMapping("/adminPage")
    @PreAuthorize("hasRole('ADMIN')")
    public String forAdmin() {
        return "This URL is For ADMIN";
    }

    @GetMapping("/userPage")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String forUser() {
        return "This URL is For User";
    }
}
