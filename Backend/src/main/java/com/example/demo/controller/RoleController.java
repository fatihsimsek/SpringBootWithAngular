package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Role;
import com.example.demo.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
	
	private RoleService roleService;
	 
    @Autowired
    public RoleController(RoleService roleService) {
        this.setRoleService(roleService);
    }
	
	@RequestMapping("/all")
	public List<Role> getAll() {
		return getRoleService().getAll();
	}
	
	@RequestMapping("/{id}")
	public Role getById(@PathVariable("id") String id) {
		return getRoleService().getById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/create")
	public void create(@RequestBody Role role) {
		getRoleService().create(role);
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
}
