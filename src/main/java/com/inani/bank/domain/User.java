package com.inani.bank.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.inani.bank.request.SignupRequest;

@Entity
public class User implements UserDetails {

    @Id
    @Column
    private String username;

    @Column
    @JsonIgnore
    private String password;

    @Column
    private String email;

    public User() {
    }

    

    public User(SignupRequest signupRequest) {
        this.username = signupRequest.getUsername();
        this.password = signupRequest.getPassword();
        this.email = signupRequest.getEmail();
    }



    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + "]";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}