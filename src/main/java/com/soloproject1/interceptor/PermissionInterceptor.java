package com.soloproject1.interceptor;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PermissionInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		String uri = request.getRequestURI();

		// 비로그인 -> recommend/favorite/comment 생성/삭제/수정, mypage X
		if (userId == null && uri.startsWith("/recommend")) {
			return false;
		}
		
		if (userId == null && uri.startsWith("/favorite/toggle")) {
			return false;
		}
		
		if (userId == null && uri.startsWith("/comment")) {
			return false;
		}
		
		if (userId == null && uri.startsWith("/mypage")) {
			return false;
		}

		// 로그인 시-> 로그인 X
		if (userId != null && uri.contains("/login")) {
			return false;
		}

		return true;

	}

}
