package com.example.demo.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.demo.model.Phone;

@Repository
public class PhoneRepositoryImpl implements PhoneRepository {

	private EntityManager entityManager;

	public PhoneRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Phone> findAll() {
		TypedQuery<Phone> query = entityManager.createQuery("from Phone", Phone.class);
		return query.getResultList();
	}

	@Override
	public Optional<Phone> findById(String id) {
		Phone phone = entityManager.find(Phone.class, id);
		return Optional.ofNullable(phone);
	}

	@Override
	@Transactional
	public void save(Phone phone) {
		String id = phone.getId();	
		if(StringUtils.isEmpty(id)) {
			phone.setId(UUID.randomUUID().toString());
			entityManager.persist(phone);
		}
		else {
			entityManager.merge(phone);
		}
	}

}
