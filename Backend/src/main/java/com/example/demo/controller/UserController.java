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
	 
    @Autowired
    public UserController(UserService userService) {
        this.setUserService(userService);
    }
	
	@RequestMapping("/all")
	public List<User> getAll() {
		return getUserService().getAll();
	}
	
	@RequestMapping("/{id}")
	public User getById(@PathVariable("id") String id) {
		return getUserService().getById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/create")
	public void create(@RequestBody User user) {
		getUserService().create(user);
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
