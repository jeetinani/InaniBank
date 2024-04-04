package com.inani.bank.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inani.bank.domain.User;
import com.inani.bank.repository.UserRepository;
import com.inani.bank.response.LoginResponse;
import com.inani.bank.service.JwtService;


@RestController
// @CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/api/users")
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /*
     * @Value("${token}")
     * private String token;
     */
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    /* @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    } */

    @GetMapping("/me")
    public ResponseEntity<User> getMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> allUsers() {
        List <User> users = userRepository.findAll();

        return ResponseEntity.ok(users);
    }
    

    /*
     * @PostMapping("/login")
     * public String login(@RequestBody @Validated User user) throws
     * AuthenticationException {
     * System.out.println(user);
     * if (matchCredentials(user)) {
     * return token;// BCrypt.hashpw("authentic", BCrypt.gensalt());
     * }
     * throw new AuthenticationException("invalid credentials");
     * }
     */

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody @Validated User user) {
        System.out.println(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody User user) {
        System.out.println(user);
        User authenticatedUser = authenticate(user);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    public User authenticate(User user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword()));

        return userRepository.findByUsername(user.getUsername()).get();
    }

    /*
     * private boolean matchCredentials(User user) {
     * Optional<User> fetched = userRepository.findByUsername(user.getUsername());
     * return fetched.isPresent() &&
     * fetched.get().getPassword().equals(user.getPassword());
     * }
     */

    /* @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Validated User user) {
        if (user.getPassword() != null && user.getUsername() != null) {
            userRepository.save(user);
        }
    } */

    /* @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.)
    public void returnBadRequest(AuthenticationException e) {
        LOGGER.error(e.getMessage(), e);
    } */
}