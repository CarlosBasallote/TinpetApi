package com.cbasalloteteba.tinpet.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cbasalloteteba.tinpet.model.User;
import com.cbasalloteteba.tinpet.repository.UserRepository;
import com.cbasalloteteba.tinpet.security.JwtUserFactory;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
        	/*for (int i = 0; i < user.getAuthorities().size(); ++i) {
        	    System.out.println("HOLAAAAAA "+user.getAuthorities().get(i));
        		}*/
            return JwtUserFactory.create(user);
        }
    }
}
