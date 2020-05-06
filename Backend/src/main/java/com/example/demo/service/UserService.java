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
		Iterable<User> iterableUser = getUserRepository().findAll();

        List<User> users = new ArrayList<User>();
        iterableUser.forEach(e -> users.add(e));

        return users;
	}
	
	public User getById(String id) {
		return getUserRepository().findById(id).get();
	}
	
	public void create(User user) {
		getUserRepository().save(user);
	}
	
	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}
