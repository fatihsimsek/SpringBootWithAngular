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

import com.example.demo.model.Phone;

@Repository
public class PhoneRepositoryImpl implements PhoneRepository {
	
	@Autowired	
	private EntityManager entityManager;
	
	private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

	@Override
	public List<Phone> findAll() {
		try(Session currentSession = getSession()) {
			Query<Phone> query = currentSession.createQuery("from Phone", Phone.class);
			return query.list();
		}
	}

	@Override
	public Optional<Phone> findById(String id) {
		try(Session currentSession = getSession()) {
			Phone phone = currentSession.get(Phone.class, id);
			return Optional.of(phone);
		}
	}

	@Override
	public void save(Phone phone) {
		Transaction transaction = null;
		String id = phone.getId();
		
		try(Session currentSession = getSession()) {
			transaction = currentSession.beginTransaction();
			
			if(StringUtils.isEmpty(id)) {
				phone.setId(UUID.randomUUID().toString());
				currentSession.save(phone);
			}
			else {
				currentSession.update(phone);
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
