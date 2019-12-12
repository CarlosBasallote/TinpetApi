package com.cbasalloteteba.tinpet.formbean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @NoArgsConstructor @AllArgsConstructor
public class RegisterFormBean {
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	
	
}
