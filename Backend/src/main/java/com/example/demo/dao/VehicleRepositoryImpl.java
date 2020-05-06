package com.example.demo.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.example.demo.model.Vehicle;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {

	@Autowired	
	private EntityManager entityManager;
	
	private Session getSession() {
        return entityManager.unwrap(Session.class);
    }
	
	@Override
	public List<Vehicle> findAll() {
		try(Session currentSession = getSession()) {
			Query<Vehicle> query = currentSession.createQuery("from Vehicle", Vehicle.class);
			return query.list();
		}
	}

	@Override
	public Optional<Vehicle> findById(String id) {
		try(Session currentSession = getSession()) {
			Vehicle vehicle = currentSession.get(Vehicle.class, id);
			return Optional.of(vehicle);
		}
	}

	@Override
	public void save(Vehicle vehicle) {
		Transaction transaction = null;
		String id = vehicle.getId();
		
		try(Session currentSession = getSession()) {
			transaction = currentSession.beginTransaction();
			
			if(StringUtils.isEmpty(id)) {
				vehicle.setId(UUID.randomUUID().toString());
				currentSession.save(vehicle);
			}
			else {
				currentSession.update(vehicle);
			}
			transaction.commit();
		}
		catch(Exception exp) {
			if(transaction != null) {
				transaction.rollback();
			}
		}
	}

}
