package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RoleRepository;
import com.example.demo.model.Role;

@Service
public class RoleService {
	private RoleRepository roleRepository;
	 
    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.setRoleRepository(roleRepository);
    }
	
	public List<Role> getAll() {
		Iterable<Role> iterableRole = getRoleRepository().findAll();

        List<Role> roles = new ArrayList<Role>();
        iterableRole.forEach(e -> roles.add(e));

        return roles;
	}
	
	public Role getById(String id) {
		return getRoleRepository().findById(id).get();
	}
	
	public void create(Role role) {
		getRoleRepository().save(role);
	}
	
	public RoleRepository getRoleRepository() {
		return roleRepository;
	}

	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
}
