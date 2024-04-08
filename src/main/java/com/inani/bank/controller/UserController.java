package com.inani.bank.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inani.bank.domain.User;
import com.inani.bank.repository.UserRepository;

@RestController
// @CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api")
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /*
     * @Value("${token}")
     * private String token;
     */
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
     * @GetMapping
     * public List<User> getUsers() {
     * return userRepository.findAll();
     * }
     */

    @GetMapping("/userName")
    public ResponseEntity<String> getMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser.getUsername());
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> allUsers() {
        List<User> users = userRepository.findAll();

        return ResponseEntity.ok(users);
    }
}