package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskController {

	// タスク一覧表示
	@GetMapping("/tasks")
	public String index(Model model) {

		// tasks.htmlを出力
		return "tasks";
	}

	// 新規登録画面の表示
	@GetMapping("/tasks/new")
	public String create() {

		// addTasks.htmlを出力
		return "addTasks";
	}

	// 新規登録処理
	@PostMapping("/tasks/new")
	public String add(Model model) {

		// 「/ToDo」にGETでリクエストし直す
		return "redirect:/tasks";
	}

}
