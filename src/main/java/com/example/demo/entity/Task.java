package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task implements Serializable {

	// フィールド
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // 主キー

	@Column(name = "category_id")
	private Integer categoryId;

	@Column(name = "user_id")
	private Integer userId;

	private String title;

	@Column(name = "closing_date")
	private LocalDate closingDate;

	private Integer progress;

	private String memo;

	@ManyToOne // taskは多、結合するcategoryは1
	@JoinColumn(name = "category_id", insertable = false, updatable = false) // 結合に使いたい列名＝外部キーの列名を書く、テーブルに操作を行うときにこのテーブルは関係ありません
	private Category category; // 結合したいテーブルのインスタンスを生成

	// コンストラクタ
	public Task() {

	}

	public Task(Integer categoryId, String title, LocalDate closingDate, Integer progress,
			String memo) {
		this.categoryId = categoryId;
		this.title = title;
		this.closingDate = closingDate;
		this.progress = progress;
		this.memo = memo;
	}

	public Task(Integer categoryId, Integer userId, String title, LocalDate closingDate, Integer progress,
			String memo) {
		this.categoryId = categoryId;
		this.userId = userId;
		this.title = title;
		this.closingDate = closingDate;
		this.progress = progress;
		this.memo = memo;
	}

	public Task(Integer id, Integer categoryId, Integer userId, String title, LocalDate closingDate, Integer progress,
			String memo) {
		this.id = id;
		this.categoryId = categoryId;
		this.userId = userId;
		this.title = title;
		this.closingDate = closingDate;
		this.progress = progress;
		this.memo = memo;
	}

	// ゲッター
	public Integer getId() {
		return id;
	}

	public Integer getUserId() {
		return userId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public String getTitle() {
		return title;
	}

	public LocalDate getClosingDate() {
		return closingDate;
	}

	public Integer getProgress() {
		return progress;
	}

	public String getMemo() {
		return memo;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
