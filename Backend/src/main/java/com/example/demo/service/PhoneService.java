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
	 
    @Autowired
    public PhoneService(PhoneRepository phoneRepository) {
        this.setPhoneRepository(phoneRepository);
    }
	
	public List<Phone> getAll() {
		Iterable<Phone> iterablePhone = getPhoneRepository().findAll();

        List<Phone> phones = new ArrayList<Phone>();
        iterablePhone.forEach(e -> phones.add(e));

        return phones;
	}
	
	public Phone getById(String id) {
		return getPhoneRepository().findById(id).get();
	}
	
	public void create(Phone phone) {
		getPhoneRepository().save(phone);
	}
	
	public PhoneRepository getPhoneRepository() {
		return phoneRepository;
	}

	public void setPhoneRepository(PhoneRepository phoneRepository) {
		this.phoneRepository = phoneRepository;
	}
}
