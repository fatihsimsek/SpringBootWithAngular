package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.VehicleRepository;
import com.example.demo.model.Vehicle;

@Service
public class VehicleService {

	private VehicleRepository vehicleRepository;
	 
    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.setVehicleRepository(vehicleRepository);
    }
	
	public List<Vehicle> getAll() {
		Iterable<Vehicle> iterableVehicle = getVehicleRepository().findAll();

        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        iterableVehicle.forEach(e -> vehicles.add(e));

        return vehicles;
	}
	
	public Vehicle getById(String id) {
		return getVehicleRepository().findById(id).get();
	}
	
	public void create(Vehicle vehicle) {
		getVehicleRepository().save(vehicle);
	}
	
	public VehicleRepository getVehicleRepository() {
		return vehicleRepository;
	}

	public void setVehicleRepository(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}
}
