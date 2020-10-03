package com.careydevelopment.ecosystem.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.careydevelopment.ecosystem.user.model.User;

public interface UserRepository extends MongoRepository<User, String> {

	public User findByUsername(String username);
	
	public User findByEmail(String email);
	
}
