package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Phone;
import com.example.demo.service.PhoneService;

@RestController
@RequestMapping("/phone")
@CrossOrigin
public class PhoneController {
	
	private PhoneService phoneService;
	 
    @Autowired
    public PhoneController(PhoneService phoneService) {
        this.setPhoneService(phoneService);
    }
	
	@RequestMapping("/list")
	public List<Phone> getAll() {
		return getPhoneService().getAll();
	}
	
	@RequestMapping("/{id}")
	public Phone getById(@PathVariable("id") String id) {
		return getPhoneService().getById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/create")
	public void create(@RequestBody Phone phone) {
		getPhoneService().create(phone);
	}

	public PhoneService getPhoneService() {
		return phoneService;
	}

	public void setPhoneService(PhoneService phoneService) {
		this.phoneService = phoneService;
	}
}
