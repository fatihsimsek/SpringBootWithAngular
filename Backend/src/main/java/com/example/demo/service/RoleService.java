package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RoleRepository;
import com.example.demo.model.Role;

@Service
public class RoleService {
	private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
	
	public List<Role> getAll() {
		Iterable<Role> iterableRole = this.roleRepository.findAll();

        List<Role> roles = new ArrayList<Role>();
        iterableRole.forEach(e -> roles.add(e));

        return roles;
	}
	
	public Role getById(String id) {
		return this.roleRepository.findById(id).get();
	}
	
	public void create(Role role) {
		this.roleRepository.save(role);
	}
	
	public Optional<Role> findByCode(String code) {
		return this.roleRepository.findByCode(code);
	}
}
