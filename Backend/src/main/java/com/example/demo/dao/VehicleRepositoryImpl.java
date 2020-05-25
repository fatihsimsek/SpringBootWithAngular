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

import com.example.demo.model.Vehicle;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {

	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	public List<Vehicle> findAll() {
		TypedQuery<Vehicle> query = entityManager.createQuery("from Vehicle", Vehicle.class);
		return query.getResultList();
	}

	@Override
	public Optional<Vehicle> findById(String id) {
		Vehicle vehicle = entityManager.find(Vehicle.class, id);
		return Optional.of(vehicle);
	}

	@Override
	@Transactional
	public void save(Vehicle vehicle) {
		String id = vehicle.getId();
		
		if(StringUtils.isEmpty(id)) {
			vehicle.setId(UUID.randomUUID().toString());
			entityManager.persist(vehicle);
		}
		else {
			entityManager.merge(vehicle);
		}
	}

}
