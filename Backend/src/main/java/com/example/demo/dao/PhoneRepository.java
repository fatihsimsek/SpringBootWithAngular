package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Phone;

public interface PhoneRepository {
	List<Phone> findAll();
	
	Optional<Phone> findById(String id);
	
	void save(Phone phone);
}
