package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class User {
	@Id
	private String id;
	private String name;
	private String surname;
	private String fullname;
	private String email;
	private String password;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "userrole",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Role> roles = new HashSet<>();
	
	public User() {
		
	}
	
	public User(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.fullname = String.format("%s %s", name, surname);
        this.email = email;
        this.password = password;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
