package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Vehicle;

public interface VehicleRepository {
	List<Vehicle> findAll();
	
	Optional<Vehicle> findById(String id);
	
	void save(Vehicle vehicle);
}
