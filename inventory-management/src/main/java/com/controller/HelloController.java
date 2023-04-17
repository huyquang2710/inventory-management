package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
	
	@GetMapping("hello")
	public String hello() {
		return "index";
	}
	
	@GetMapping("login")
	public String login() {
		return "login/login";
	}
}
 