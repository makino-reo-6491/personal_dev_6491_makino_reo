package com.example.demo.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.model.Account;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class UserAdminController {

	@Autowired
	HttpSession session;

	@Autowired
	UserRepository userRepository;

	@Autowired
	Account account;

	// 管理者ログイン画面表示
	@GetMapping("/login")
	public String index() {

		// セッション破棄
		session.invalidate();
		return "admin/login";
	}

	// 管理者ログインを実行
	@PostMapping("/login")
	public String login(
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			Model model) {

		// ログインチェック
		if (!email.equals("admin@aaa.com") || !password.equals("himitu")) {
			model.addAttribute("message", "ユーザ名とパスワードが一致しませんでした");
			return "admin/login";
		}

		account.setName("admin");
		return "redirect:/admin/managements";
	}

	//	ユーザー情報一覧表示
	@GetMapping("/users")
	public String index(Model model) {

		List<User> Users = userRepository.findAll();
		model.addAttribute("users", Users);

		return "admin/users";
	}

	// 新規ユーザー追加画面表示
	@GetMapping("/users/new")
	public String create() {
		return "admin/accountForm";
	}

	// 新規ユーザー追加実行
	@PostMapping("/users/add")
	public String add(
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("password2") String password2,
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
		if (password2.equals(password) == false) {
			errorList.add("パスワードが一致しません");
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
			model.addAttribute("password2", password2);
			return "admin/accountForm";
		}

		User user = new User(email, name, password);
		userRepository.save(user);

		return "redirect:/admin/users";
	}

	// 更新画面表示
	@GetMapping("/users/{id}/edit")
	public String edit(
			@PathVariable("id") Integer id,
			Model model) {

		// usersテーブルを主キーで検索
		User user = userRepository.findById(id).get();
		model.addAttribute("user", user);

		return "admin/editUsers";
	}

	//	ユーザー情報更新
	@PostMapping("/users/{id}/edit")
	public String update(
			@PathVariable("id") Integer id,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "password", defaultValue = "") String password,
			@RequestParam(name = "password2", defaultValue = "") String password2,
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
		if (password2.equals(password) == false) {
			errorList.add("パスワードが一致しません");
		}
		// メールアドレス存在チェック
		List<User> userList = userRepository.findByEmailAndPassword(email, password);
		if (userList != null && userList.size() > 0) {
			// 登録済みのメールアドレスが存在した場合
			errorList.add("登録済みのメールアドレスとパスワードの組み合わせです");
		}
		//		if (userList == null || userList.size() == 0) {
		//			// 存在しなかった場合
		//			model.addAttribute("message", "メールアドレスとパスワードが一致しませんでした");
		//		}
		//
		// エラー発生時は新規登録フォームに戻す
		if (errorList.size() > 0) {
			model.addAttribute("errorList", errorList);
			User user = new User(id, email, name, password);
			model.addAttribute(user);
			return "admin/editUsers";
		}

		// Userオブジェクトの生成
		User user = new User(id, email, name, password);
		// usersテーブルへの反映
		userRepository.save(user);

		// 「/admin/users」にリダイレクトし直す
		return "redirect:/admin/users";

	}

	// 削除処理
	@PostMapping("/users/{id}/delete")
	public String delete(@PathVariable("id") Integer id,
			Model model) {

		// usersテーブルから削除（DELETE）
		userRepository.deleteById(id);
		// 「/users」にリダイレクトし直す
		return "redirect:/admin/users";
	}

}
