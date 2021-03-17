package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserRepository;
import com.example.demo.model.User;

@Service
public class UserService {
	private UserRepository userRepository;
	 
    @Autowired
    public UserService(UserRepository userRepository) {
        this.setUserRepository(userRepository);
    }
	
	public List<User> getAll() {
		Iterable<User> iterableUser = this.userRepository.findAll();

        List<User> users = new ArrayList<User>();
        iterableUser.forEach(e -> users.add(e));

        return users;
	}
	
	public User getById(String id) {
		return this.userRepository.findById(id).get();
	}
	
	public void save(User user) {
		this.userRepository.save(user);
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public boolean existsByEmail(String email) {
		return this.userRepository.existsByEmail(email);
	}
}