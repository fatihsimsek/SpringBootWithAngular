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

import com.example.demo.model.User;

@Repository
public class UserRepositoryImpl implements UserRepository{

	@PersistenceContext	
	private EntityManager entityManager;
	
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

}
