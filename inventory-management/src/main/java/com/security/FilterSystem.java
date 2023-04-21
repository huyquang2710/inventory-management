package com.security;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;

import com.dao.BaseDAOImpl;
import com.model.Auth;
import com.model.UserRole;
import com.model.Users;
import com.util.Constant;

public class FilterSystem implements HandlerInterceptor {
	final static Logger log = Logger.getLogger(BaseDAOImpl.class);

	// khi url di qua spring, thi no se di qua day truoc, roi moi di qua controller
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		log.info("Request URI = " + request.getRequestURI());

		// check session, xem user da login chua
		// neu chua thi redirect -> /login
		Users user = (Users) request.getSession().getAttribute(Constant.USER_INFO);
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return false;
		}
		if (user != null) {
			String url = request.getServletPath();
			if (!hasPermission(url, user)) {
				response.sendRedirect(request.getContextPath() + "/access-denied");
				return false;
			}
		}

		return true;
	}

	// check user co quyen truy cap url hay ko?
	private boolean hasPermission(String url, Users user) {

		if (url.contains("/index") || url.contains("/access-denied") || url.contains("/logout")) {
			return true;
		}

		UserRole userRole = (UserRole) user.getUserRoles().iterator().next();
		Set<Auth> auths = userRole.getRole().getAuths();
		for (Object obj : auths) {
			Auth auth = (Auth) obj;
			if (url.contains(auth.getMenu().getUrl())) {
				return auth.getPermission() == 1;
			}
		}
		return false;
	}
}
