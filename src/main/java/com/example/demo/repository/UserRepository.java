package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	// SELECT * FROM users WHERE name LIKE ?
	List<User> findByNameContaining(String keyword);

	// SELECT * FROM users WHERE email = ? AND password = ?
	List<User> findByEmailAndPassword(String email, String password);

	// SELECT * FROM users WHERE email = ? 
	List<User> findByEmail(String email);

	// SELECT * FROM users WHERE password = ?
	List<User> findByPassword(String password);

}