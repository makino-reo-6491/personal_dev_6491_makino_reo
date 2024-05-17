package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.model.Account;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	HttpSession session;

	@Autowired
	Account account;

	@Autowired
	UserRepository userRepository;

	// ログイン画面を表示
	@GetMapping({ "/", "/login", "logout" })
	public String index(
			@RequestParam(name = "error", defaultValue = "") String error,
			Model model) {
		// セッション情報をすべてクリアする
		session.invalidate();
		// エラーパラメータのチェック
		if (error.equals("notLoggedIn")) {
			model.addAttribute("message", "ログインしてください");
		}

		return "login";
	}

	// ログインを実行
	@PostMapping("/login")
	public String login(
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			Model model) {

		// エラーチェック
		List<String> errorList = new ArrayList<>();
		if (email.length() == 0) {
			errorList.add("メールアドレスは必須です");
		}
		if (password.length() == 0) {
			errorList.add("パスワードは必須です");
		}

		// エラー発生時はログイン画面に戻す
		if (errorList.size() > 0) {
			model.addAttribute("errorList", errorList);
			model.addAttribute("email", email);
			model.addAttribute("password", password);
			return "login";
		}

		List<User> userList = userRepository.findByEmailAndPassword(email, password);
		if (userList == null || userList.size() == 0) {
			// 存在しなかった場合
			model.addAttribute("message", "メールアドレスとパスワードが一致しませんでした");
			return "login";
		}
		//		User user = userList.get(0);
		User user = new User(email, password);
		userRepository.save(user);

		// セッション管理されたアカウント情報にIDと名前をセット
		//		account.setId(customer.getId());
		account.setName(user.getName());

		//	「/tasks」にGETでリクエストし直す
		return "redirect:/tasks";
	}

	// ユーザー登録フォームを表示する
	@GetMapping("/users/new")
	public String create() {
		return "accountForm";
	}

	// 会員登録実行
	@PostMapping("/users/add")
	public String add(
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			Model model) {

		// エラーチェック
		List<String> errorList = new ArrayList<>();
		if (name.length() == 0) {
			errorList.add("名前は必須です");
		}
		if (email.length() == 0) {
			errorList.add("メールアドレスは必須です");
		}
		if (password.length() == 0) {
			errorList.add("パスワードは必須です");
		}
		// メールアドレス存在チェック
		List<User> userList = userRepository.findByEmailAndPassword(email, password);
		if (userList != null && userList.size() > 0) {
			// 登録済みのメールアドレスが存在した場合
			errorList.add("登録済みのメールアドレスです");
		}
		if (userList == null || userList.size() == 0) {
			// 存在しなかった場合
			model.addAttribute("message", "メールアドレスとパスワードが一致しませんでした");
		}

		// エラー発生時は新規登録フォームに戻す
		if (errorList.size() > 0) {
			model.addAttribute("errorList", errorList);
			model.addAttribute("name", name);
			model.addAttribute("email", email);
			model.addAttribute("password", password);
			return "accountForm";
		}

		User user = new User(email, name, password);
		userRepository.save(user);

		return "redirect:/";
	}

}
