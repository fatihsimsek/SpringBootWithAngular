package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PhoneRepository;
import com.example.demo.model.Phone;

@Service
public class PhoneService {

	private PhoneRepository phoneRepository;

    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }
	
	public List<Phone> getAll() {
		Iterable<Phone> iterablePhone = this.phoneRepository.findAll();

        List<Phone> phones = new ArrayList<Phone>();
        iterablePhone.forEach(e -> phones.add(e));

        return phones;
	}
	
	public Phone getById(String id) {
		return this.phoneRepository.findById(id).get();
	}
	
	public void create(Phone phone) {
		this.phoneRepository.save(phone);
	}
}
