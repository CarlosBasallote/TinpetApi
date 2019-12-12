package com.cbasalloteteba.tinpet.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.cbasalloteteba.tinpet.model.Authority;
import com.cbasalloteteba.tinpet.model.User;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
    	for (int i = 0; i < user.getAuthorities().size(); ++i) {
    	    System.out.println("HOLAAAAAA "+user.getAuthorities().get(i));
    	}
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPhone(),
                user.getPassword(),
                user.getPet(),
                mapToGrantedAuthorities(user.getAuthorities())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
    	/*for (int i = 0; i < authorities.size(); ++i) {
    	    System.out.println("HOLAAAAAA "+authorities.get(i));
    	}*/
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList());
    }
}
