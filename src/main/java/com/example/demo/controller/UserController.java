package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

	// ログイン画面を表示
	@GetMapping({ "/", "/login", "logout" })
	public String index(
			@RequestParam(name = "error", defaultValue = "") String error,
			Model model) {

		return "login";
	}

	// ログインを実行
	@PostMapping("/login")
	public String login(
			@RequestParam("name") String name,
			Model model) {

		// 「/ToDo」へのリダイレクト
		return "redirect:/tasks";
	}

}
