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

import com.example.demo.model.Role;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

	private EntityManager entityManager;

	public RoleRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<Role> findAll() {
		TypedQuery<Role> query = entityManager.createQuery("from Role", Role.class);
		return query.getResultList();
	}

	@Override
	public Optional<Role> findById(String id) {
		Role role = entityManager.find(Role.class, id);
		return Optional.of(role);
	}
	
	@Override
	public Optional<Role> findByCode(String code) {
		TypedQuery<Role> query = entityManager.createQuery("from Role Where Code=:Code", Role.class)
	            				.setParameter("Code", code);

		List<Role> roles = query.getResultList();
		
		return roles.size() > 0 ? Optional.of(roles.get(0)) : Optional.of(null);
	}

	@Override
	@Transactional
	public void save(Role role) {
		String id = role.getId();

		if(StringUtils.isEmpty(id)) {
			role.setId(UUID.randomUUID().toString());
			entityManager.persist(role);
		}
		else {
			entityManager.merge(role);
		}
	}

}
