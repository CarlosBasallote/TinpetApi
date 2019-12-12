package com.cbasalloteteba.tinpet.formbean;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUserDto {

	private Long id;
	private String email;
	private String username;
	private String token;

}

