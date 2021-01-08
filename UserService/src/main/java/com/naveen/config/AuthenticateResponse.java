package com.naveen.config;

public class AuthenticateResponse {
	
	
	private String token;

	public AuthenticateResponse(String token) {
		this.token = token;
	}

	public AuthenticateResponse() {
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}


}
