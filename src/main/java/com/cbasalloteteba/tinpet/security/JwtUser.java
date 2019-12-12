package com.cbasalloteteba.tinpet.security;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cbasalloteteba.tinpet.model.Pet;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by stephan on 20.03.16.
 */
public class JwtUser implements UserDetails {

    private final Long id;
    private final String username;
    private final String firstname;
    private final String lastname;
    private final String password;
    private final String email;
    private final String phone;
    private Pet pet;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(
          Long id,
          String username,
          String firstname,
          String lastname,
          String email,
          String phone,
          String password,
          Pet pet,
          Collection<? extends GrantedAuthority> authorities
    ) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone= phone;
        this.password = password;
        this.pet=pet;
        this.authorities = authorities;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public Pet getPet() {
        return pet;
    }
    
    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}



}
