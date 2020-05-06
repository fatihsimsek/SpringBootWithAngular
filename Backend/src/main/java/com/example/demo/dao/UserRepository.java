package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.User;

public interface UserRepository {
	List<User> findAll();
	
	Optional<User> findById(String id);
	
	Optional<User> findByEmail(String email);
	
	boolean existsByEmail(String email);
	
	void save(User user);
}
