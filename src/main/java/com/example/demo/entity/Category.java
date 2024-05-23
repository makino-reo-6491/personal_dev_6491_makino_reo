package com.example.demo.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Category implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // カテゴリーID

	private String name; // カテゴリー名

	//	@OneToMany(mappedBy = "")
	//	private List<Task> tasks;

	//	コンストラクタ
	public Category() {

	}

	public Category(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Category(String name) {
		this.name = name;
	}

	// ゲッター
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

}
