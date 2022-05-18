package com.ecommerce.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.User;

public interface UserDao extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String name);
}
