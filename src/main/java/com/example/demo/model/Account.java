package com.example.demo.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class Account {

	// アカウント名

	private Integer id;

	private String name;

	// フィールド
	public Account() {

	}

	public Account(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	// ゲッター、セッター
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
