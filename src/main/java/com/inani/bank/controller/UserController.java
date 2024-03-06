package com.inani.bank.controller;

import java.util.List;
import java.util.Optional;

import javax.security.sasl.AuthenticationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inani.bank.domain.User;
import com.inani.bank.repository.UserRepository;


@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/users")
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Value("${token}")
    private String token;
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    


    @PostMapping("/login")
    public String login(@RequestBody @Validated User user) throws AuthenticationException {
        System.out.println(user);
        if (matchCredentials(user)) {
            return token;// BCrypt.hashpw("authentic", BCrypt.gensalt());
        }
        throw new AuthenticationException("invalid credentials");
    }

    private boolean matchCredentials(User user) {
        Optional<User> fetched = userRepository.findByUsername(user.getUsername());
        return fetched.isPresent() && fetched.get().getPassword().equals(user.getPassword());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Validated User user) {
        if (user.getPassword() != null && user.getUsername() != null) {
            userRepository.save(user);
        }
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void returnBadRequest(AuthenticationException e) {
        LOGGER.error(e.getMessage(), e);
    }
}
