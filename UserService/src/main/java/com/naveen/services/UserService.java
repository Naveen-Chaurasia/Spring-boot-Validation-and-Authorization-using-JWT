package com.naveen.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naveen.models.User;
import com.naveen.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<User> getUsers() {

		return userRepository.findAll();
	}

	public void addUsers(User u) {

		userRepository.save(u);
		System.out.println("Added User with name: " + u);
	}

	public void deleteUser(String name) {
		User user = userRepository.findByName(name);
		userRepository.delete(user);

	}


	public void updateUser(User c) {
		userRepository.save(c);
		
	}

}
