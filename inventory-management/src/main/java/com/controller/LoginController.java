package com.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

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

import com.model.Auth;
import com.model.Menu;
import com.model.Role;
import com.model.UserRole;
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
		// login
		Users user = userService.login(users.getUserName(), users.getPassword());

		// lay role
		// Duyệt các phần tử từ đầu đến cuối của một collection.
		// Iterator cho phép xóa phần tử khi lặp một collection.
		// lay role tu user
		UserRole userRole = (UserRole) user.getUserRoles().iterator().next();

		List<Menu> menuList = new ArrayList<>();
		List<Menu> menuChildList = new ArrayList<>();

		Role role = userRole.getRole();

		// dua theo user -> lay dc role
		// dua vao role -> lay dc all quyen: query toi authorty
		// dua vao auth se xuat duoc menu theo quyen dc phan.

		// 1. tim menu cha truoc
		for (Object obj : role.getAuths()) {
			Auth auth = (Auth) obj;
			Menu menu = auth.getMenu();
			// menu cha se co
			// -> menu {parentId = 0 , orderIndex != -1, activeFlag = 1}
			// -> auth {permisstion = 1, activeFlag = 1}
			if (menu.getParentId() == 0 && menu.getOrderIndex() != -1 && menu.getActiveFlag() == 1
					&& auth.getPermission() == 1 && auth.getActiveFlag() == 1) {

				replaceUrlToId(menu);
				menuList.add(menu);
			} else if (menu.getParentId() != 0 && menu.getOrderIndex() != -1 && menu.getActiveFlag() == 1
					&& auth.getPermission() == 1 && auth.getActiveFlag() == 1) {
				// menu cha se co
				// -> menu {parentId != 0 , orderIndex != -1, activeFlag = 1}
				// -> auth {permisstion = 1, activeFlag = 1}

				replaceUrlToId(menu);
				menuChildList.add(menu);
			}
		}

		// dua vao menu cha de ep con vao
		// 1 cha se ep 1 list con vao
		for (Menu menu : menuList) {
			List<Menu> childList = new ArrayList<>();
			for (Menu childMenu : menuChildList) {
				if (childMenu.getParentId() == menu.getId()) {
					childList.add(childMenu);
				}
			}
			menu.setChild(childList);
		}

		sortMenu(menuList);
		for (Menu menu : menuList) {
			sortMenu(menuChildList);
		}

		// login thanh cong se luu thong tin user vao session
		session.setAttribute(Constant.USER_INFO, user);

		// menu
		session.setAttribute(Constant.MENU_SESSION, menuList);

		return "redirect:/index";
	}

	// sort menu
	private void sortMenu(List<Menu> menus) {
		Collections.sort(menus, new Comparator<Menu>() {
			@Override
			public int compare(Menu o1, Menu o2) {
				// TODO Auto-generated method stub
				return o1.getOrderIndex() - o2.getOrderIndex();
			}
		});
	}

	// convert thanh Id, de gan cho menu tren JSP.
	// VD: /product-info => product-infoId
	private void replaceUrlToId(Menu menu) {
		menu.setIdMenu(menu.getUrl().replace("/", "") + Constant.ID);
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
