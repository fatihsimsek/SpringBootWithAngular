package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
	
	@RequestMapping("/all")
	public List<User> getAll() {
		return this.userService.getAll();
	}
	
	@RequestMapping("/{id}")
	public User getById(@PathVariable("id") String id) {
		return this.userService.getById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/create")
	public void create(@RequestBody User user) {
		this.userService.save(user);
	}
}
