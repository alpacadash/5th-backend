package com.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class CookieController {
	@GetMapping("cookie-test")
	public String cookieTest(HttpServletResponse response) {
		
		// 생성
		Cookie c1 = new Cookie("cookie-id", "cookie-value");
		c1.setMaxAge(60 * 60);
		response.addCookie(c1);
		
		return "cookie";
	}
	
	@GetMapping("cookie-check")
	public String cookieCheck(HttpServletRequest request) {
		
		// 쿠키 확인(클라이언트 -> 서버 자동으로 요청 객체 포함)
		Cookie[] cookies = request.getCookies();
		
		for(Cookie cookie : cookies) {
			System.out.println(cookie.getName());
			System.out.println(cookie.getValue());
			System.out.println();
		}
		
		return "cookie";
	}
}
