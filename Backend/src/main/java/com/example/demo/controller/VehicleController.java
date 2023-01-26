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

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }
	
	@RequestMapping("/list")
	public List<Vehicle> getAll() {
		return this.vehicleService.getAll();
	}
	
	@RequestMapping("/{id}")
	public Vehicle getById(@PathVariable("id") String id) {
		return this.vehicleService.getById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/create")
	public void create(@RequestBody Vehicle vehicle) {
		this.vehicleService.create(vehicle);
	}

}
