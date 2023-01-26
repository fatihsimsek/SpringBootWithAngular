package com.example.demo.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Future;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.demo.exception.AsyncExecutionException;
import com.example.demo.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository{

	private EntityManager entityManager;

	public UserRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<User> findAll() {
		TypedQuery<User> query = entityManager.createQuery("from User", User.class);
		return query.getResultList();
	}

	@Override
	public Optional<User> findById(String id) {
		User user = entityManager.find(User.class, id);
		return Optional.of(user);
	}
	
	@Override
	public Optional<User> findByEmail(String email) {
		TypedQuery<User> query = entityManager.createQuery("from User Where Email=:Email", User.class)
				            	.setParameter("Email", email);
		
		List<User> users = query.getResultList();
		
		return users.size() > 0 ? Optional.of(users.get(0)) : Optional.of(null);
	}
	
	@Override
	public boolean existsByEmail(String email) {
		Optional<User> user = findByEmail(email);
		return user.isPresent();
	}

	@Override
	@Transactional
	public void save(User user) {
		String id = user.getId();
		
		if(StringUtils.isEmpty(id)) {
			user.setId(UUID.randomUUID().toString());
			entityManager.persist(user);
		}
		else {
			entityManager.merge(user);
		}
	}
	
	@Async
	@Override
	public void asyncCall() throws Exception {
		System.out.println("Execute method asynchronously - " + Thread.currentThread().getName());
	    
	    boolean control = true;
		if(control  == true) {
	    	throw new Exception("Error occured");
	    }
	}
	
	@Async
	@Override
	public Future<String> asyncCallWithReturn() throws Exception {
		System.out.println("Execute method asynchronously return - " + Thread.currentThread().getName());
	    
	    boolean control = true;
		if(control  == true) {
	    	throw new AsyncExecutionException("Error occured-2");
	    }
		
		return new AsyncResult<String>("Hello"); 
	}

}
