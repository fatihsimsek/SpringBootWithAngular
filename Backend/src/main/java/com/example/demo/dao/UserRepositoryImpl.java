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

import com.example.demo.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository{

	@Autowired	
	private EntityManager entityManager;
	
	private Session getSession() {
        return entityManager.unwrap(Session.class);
    }
	
	@Override
	public List<User> findAll() {
		try(Session currentSession = getSession()) {
			Query<User> query = currentSession.createQuery("from User", User.class);
			return query.list();
		}
	}

	@Override
	public Optional<User> findById(String id) {
		try(Session currentSession = getSession()) {
			User user = currentSession.get(User.class, id);
			return Optional.of(user);
		}
	}
	
	@Override
	public Optional<User> findByEmail(String email) {
		try(Session currentSession = getSession()) {
			Query<User> query = currentSession.createQuery("from User Where Email=:Email", User.class)
					            .setParameter("Email", email);
			
			List<User> users = query.list();
			
			return users.size() > 0 ? Optional.of(users.get(0)) : Optional.of(null);
		}
	}
	
	@Override
	public boolean existsByEmail(String email) {
		Optional<User> user = findByEmail(email);
		return user.isPresent();
	}

	@Override
	public void save(User user) {
		Transaction transaction = null;
		String id = user.getId();
		
		try(Session currentSession = getSession()) {
			transaction = currentSession.beginTransaction();
			
			if(StringUtils.isEmpty(id)) {
				user.setId(UUID.randomUUID().toString());
				currentSession.save(user);
			}
			else {
				currentSession.update(user);
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
