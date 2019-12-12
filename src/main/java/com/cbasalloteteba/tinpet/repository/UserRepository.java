package com.cbasalloteteba.tinpet.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cbasalloteteba.tinpet.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    
}
