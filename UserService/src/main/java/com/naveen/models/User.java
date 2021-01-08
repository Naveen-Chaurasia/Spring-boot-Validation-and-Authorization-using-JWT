package com.naveen.models;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "name","password","role" })

public class User {
	@Id
	public String id;
	
	@JsonProperty(value = "name")
	private String name;
	
	
@JsonProperty(value = "password")
	private String password;

@JsonProperty(value = "role")
private String role;

	public String getPassword() {
		return password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

	public User() {
	}
	public User(String name) {
		this.name=name;
	}

	public User(String name, String id, String role) {
		this.id = id;
		this.name = name;
		this.role = role;
	}

}
