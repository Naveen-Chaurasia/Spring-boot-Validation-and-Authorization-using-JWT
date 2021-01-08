package com.naveen.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.naveen.models.User;

public interface UserRepository extends MongoRepository<User, String> {
	public User findByName(String name);

	//public List<User> findByLastName(String lastName);
}
