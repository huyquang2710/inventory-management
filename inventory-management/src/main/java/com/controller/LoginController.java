package com.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.model.Users;
import com.service.UserService;
import com.util.Constant;

@Controller
@ComponentScan("com")
public class LoginController {

	@Autowired(required = true)
	private UserService userService;
//	@Autowired
//	private LoginValidator loginValidator;

//	// rang buoc du lieu
//	// kiem tra data duoc truyen tu web co thuoc trong Users hay ko?
//	@InitBinder
//	private void initBinder(WebDataBinder webDataBinder) {
//		if (webDataBinder.getTarget() == null)
//			return;
//		if (webDataBinder.getTarget().getClass() == Users.class) {
//			webDataBinder.setValidator(loginValidator);
//		}
//	}

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("loginForm", new Users());

		return "login/login";
	}

	@PostMapping("/processLogin")
	public String processLogin(@Valid @ModelAttribute("loginForm") Users users, Model model,
			BindingResult bindingResult, HttpSession session) {

		// check validate
		if (bindingResult.hasErrors()) {
			return "login/login";
		}

		Users user = userService.login(users.getUserName(), users.getPassword());

		// login thanh cong se luu thong tin user vao session
		session.setAttribute(Constant.USER_INFO, user);

		return "redirect:/index";
	}

//	@PostMapping("/processLogin")
//	public String processLogin(Model model, @ModelAttribute("loginForm") @Validated Users users,
//			BindingResult bindingResult, HttpSession session) {
//
//		// check validate
//		if (bindingResult.hasErrors()) {
//			return "login/login";
//		}
//
//		Users user = userService.loginUser(users);
//		
//		// login thanh cong se luu thong tin user vao session
//		session.setAttribute(Constant.USER_INFO, user);
//
//		return "redrect:/index";
//	}
}
