package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Role;

public interface RoleRepository {
	List<Role> findAll();
	
	Optional<Role> findById(String id);
	
	Optional<Role> findByCode(String code);
	
	void save(Role role);
}
