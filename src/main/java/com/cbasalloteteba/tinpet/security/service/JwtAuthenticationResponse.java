package com.cbasalloteteba.tinpet.security.service;

import java.io.Serializable;

public class JwtAuthenticationResponse implements Serializable {

	private static final long serialVersionUID = 1250166508152483573L;

	private final String token;
	private String username;
	private String email;
	private String rol;

	public JwtAuthenticationResponse(String token, String username, String rol, String email) {
		this.token = token;
		this.username = username;
		this.rol = rol;
		this.email = email;
	}

	public JwtAuthenticationResponse(String token) {
		super();
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public String getRol() {
		return rol;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUsername() {
		return username;
	}

	public String getToken() {
		return this.token;
	}
}
