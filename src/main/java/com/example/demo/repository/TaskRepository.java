package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

	List<Task> findByCategoryId(Integer categoryId);

	List<Task> findByUserId(Integer userId);

	List<Task> findByOrderByClosingDateAsc();

	List<Task> findByOrderByTitleAsc();

	List<Task> findByTitleContaining(String keyword);

	//	List<Task> findByOrderByInportanceAsc();

}
