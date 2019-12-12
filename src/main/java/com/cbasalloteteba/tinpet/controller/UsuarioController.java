package com.cbasalloteteba.tinpet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbasalloteteba.tinpet.model.User;
import com.cbasalloteteba.tinpet.repository.UserRepository;

@RestController 
public class UsuarioController {
	
	@Autowired
	UserRepository repository;
	
	@GetMapping("auth/user")
	public List<User> showListUser(){
		return repository.findAll();
	}
}
