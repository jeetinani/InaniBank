package com.inani.bank.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User implements UserDetails {
    /*
     * @Id
     * 
     * @Column
     * 
     * @GeneratedValue(strategy = GenerationType.IDENTITY)
     * private Long id;
     */

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

    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + "]";
    }

    /*
     * public Long getId() {
     * return id;
     * }
     * 
     * public void setId(Long id) {
     * this.id = id;
     * }
     */

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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}