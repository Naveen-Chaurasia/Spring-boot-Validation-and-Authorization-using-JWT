package com.naveen.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.naveen.models.User;
import com.naveen.services.UserService;

@RestController
public class Controller {

	@Autowired
	UserService userService;

	@GetMapping("/users")
	public List<User> getAllUsers() {

		List<User> user = userService.getUsers();
		return user;

	}

	@PostMapping("/adduser")
	public void addUser(@RequestBody User u) {

		userService.addUsers(u);
	}

	@DeleteMapping("/deleteuser")
	public void deleteUser(@RequestParam String name) {
		userService.deleteUser(name);

	}

	@PutMapping("/updateuser")
	public void updateUser(@RequestBody User c) {
		userService.updateUser(c);
	}

}
