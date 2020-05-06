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

import com.example.demo.model.Role;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

	@Autowired	
	private EntityManager entityManager;
	
	private Session getSession() {
        return entityManager.unwrap(Session.class);
    }
	
	@Override
	public List<Role> findAll() {
		try(Session currentSession = getSession()) {
			Query<Role> query = currentSession.createQuery("from Role", Role.class);
			return query.list();
		}
	}

	@Override
	public Optional<Role> findById(String id) {
		try(Session currentSession = getSession()) {
			Role role = currentSession.get(Role.class, id);
			return Optional.of(role);
		}
	}
	
	@Override
	public Optional<Role> findByCode(String code) {
		try(Session currentSession = getSession()) {
			Query<Role> query = currentSession.createQuery("from Role Where Code=:Code", Role.class)
		            .setParameter("Code", code);

			List<Role> roles = query.list();
			
			return roles.size() > 0 ? Optional.of(roles.get(0)) : Optional.of(null);
		}
	}

	@Override
	public void save(Role role) {
		Transaction transaction = null;
		String id = role.getId();
		
		try(Session currentSession = getSession()) {
			transaction = currentSession.beginTransaction();
			
			if(StringUtils.isEmpty(id)) {
				role.setId(UUID.randomUUID().toString());
				currentSession.save(role);
			}
			else {
				currentSession.update(role);
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
