//package com.validate;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.validation.Errors;
//import org.springframework.validation.ValidationUtils;
//import org.springframework.validation.Validator;
//
//import com.model.Users;
//import com.service.UserService;
//
//@Component
//public class LoginValidator implements Validator {
//
//	@Autowired
//	private UserService userService;
//
//	public boolean supports(Class<?> clazz) {
//
//		// chi support class Users
//		return clazz == Users.class;
//	}
//
//	// check validate userName, password
//	public void validate(Object target, Errors errors) {
//
//		// cast Object thanh Users
//		Users user = (Users) target;
//
//		// khi user login, se check xem user, pass da dien thong tin chuaw?
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "msg.required");
//		ValidationUtils.rejectIfEmpty(errors, "password", "msg.required");
//
//		// check username, password co ton tai ko?
//		if (!StringUtils.isEmpty(user.getUserName()) && !StringUtils.isEmpty(user.getPassword())) {
//			Users users = userService.login("userName", "password");
//
//			// check username
//			if (user != null) {
//				// check password
//				if (!users.get(0).getPassword().equals(user.getPassword())) {
//					errors.rejectValue("password", "msg.wrong.password");
//				}
//			} else {
//				errors.rejectValue("userName", "msg.wrong.userName");
//			}
//		}
//
//	}
//
//}
