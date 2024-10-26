package com.inani.bank.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.inani.bank.domain.User;
import com.inani.bank.repository.UserRepository;
import com.inani.bank.request.SignupRequest;

@Service
public class UserService {
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${default.user.name:admin}")
    private String defaultUserName;

    

    public boolean isDefaultUserPresent(){
        return userRepository.findByUsername(defaultUserName).isPresent();
    }

    public void signUp(SignupRequest signupRequest) {
        System.out.println(signupRequest);
        signupRequest.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        userRepository.save(new User(signupRequest));
    }
}
