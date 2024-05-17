package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Task;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.TaskRepository;

@Controller
public class TaskController {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	TaskRepository taskRepository;

	// タスク一覧表示
	@GetMapping("/tasks")
	public String index(
			@RequestParam(value = "categoryId", defaultValue = "") Integer categoryId,
			Model model) {

		// 全カテゴリー一覧を取得
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);

		// タスク一覧情報の取得
		List<Task> taskList = null;
		if (categoryId == null) {
			taskList = taskRepository.findAll();
		} else {
			// tasksテーブルをカテゴリーIDを指定して一覧を取得
			taskList = taskRepository.findByCategoryId(categoryId);
		}
		model.addAttribute("tasks", taskList);

		// tasks.htmlを出力
		return "tasks";
	}

	// 新規登録画面の表示
	@GetMapping("/tasks/add")
	public String create() {

		// addTasks.htmlを出力
		return "addTasks";
	}

	// 新規登録処理
	@PostMapping("/tasks/add")
	public String add(
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "title", defaultValue = "") String title,
			@RequestParam(name = "closingDate", defaultValue = "") LocalDate closingDate,
			@RequestParam(name = "progress", defaultValue = "") Integer progress,
			@RequestParam(name = "memo", defaultValue = "") String memo,
			Model model) {

		// エラーチェック
		List<String> errorList = new ArrayList<>();
		if (title.length() == 0) {
			errorList.add("タスク名は必須です");
		}
		if (memo.length() == 0) {
			errorList.add("内容を入力してください");
		}

		// エラー発生時は新規登録フォームに戻す
		if (errorList.size() > 0) {
			model.addAttribute("errorList", errorList);
			model.addAttribute("title", title);
			model.addAttribute("memo", memo);
			return "addtasks";
		}

		Task task = new Task(categoryId, title, closingDate, progress, memo);
		taskRepository.save(task);

		// 「/tasks」にGETでリクエストし直す
		return "redirect:/tasks";
	}

	// 更新/削除ボタン表示
	@GetMapping("/tasks/edit")
	public String detail(
			@RequestParam(value = "categoryId", defaultValue = "") Integer categoryId,
			Model model) {

		// 全カテゴリー一覧を取得
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);

		// タスク一覧情報の取得
		List<Task> taskList = null;
		if (categoryId == null) {
			taskList = taskRepository.findAll();
		} else {
			// tasksテーブルをカテゴリーIDを指定して一覧を取得
			taskList = taskRepository.findByCategoryId(categoryId);
		}
		model.addAttribute("tasks", taskList);

		// tasks.htmlを出力
		return "tasksDetail";
	}

	// 更新画面表示
	@GetMapping("/tasks/{id}/edit")
	public String edit(
			@PathVariable("id") Integer id,
			Model model) {

		// tasksテーブルをID（主キー）で検索
		Task task = taskRepository.findById(id).get();
		model.addAttribute("task", task);

		return "editTasks";
	}

	// 更新処理
	@PostMapping("/tasks/{id}/edit")
	public String update(
			@PathVariable("id") Integer id,
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "title", defaultValue = "") String title,
			@RequestParam(name = "closingDate", defaultValue = "") LocalDate closingDate,
			@RequestParam(name = "progress", defaultValue = "") Integer progress,
			@RequestParam(name = "memo", defaultValue = "") String memo,
			Model model) {

		// Taskオブジェクトの生成
		Task task = new Task(id, categoryId, title, closingDate, progress, memo);
		// tasksテーブルへの反映
		taskRepository.save(task);

		// 「/tasks」にリダイレクトし直す
		return "redirect:/tasks";
	}

	// 削除処理
	@PostMapping("/tasks/{id}/delete")
	public String delete(@PathVariable("id") Integer id,
			Model model) {

		// tasksテーブルから削除（DELETE）
		taskRepository.deleteById(id);
		// 「/tasks」にGETでリクエストし直す
		return "redirect:/tasks";
	}

}