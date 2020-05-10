package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Vehicle;
import com.example.demo.service.VehicleService;

@RestController
@RequestMapping("/vehicle")
@CrossOrigin
public class VehicleController {
	
	private VehicleService vehicleService;
	 
    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.setVehicleService(vehicleService);
    }
	
	@RequestMapping("/list")
	public List<Vehicle> getAll() {
		return getVehicleService().getAll();
	}
	
	@RequestMapping("/{id}")
	public Vehicle getById(@PathVariable("id") String id) {
		return getVehicleService().getById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/create")
	public void create(@RequestBody Vehicle vehicle) {
		getVehicleService().create(vehicle);
	}

	public VehicleService getVehicleService() {
		return vehicleService;
	}

	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}
}
