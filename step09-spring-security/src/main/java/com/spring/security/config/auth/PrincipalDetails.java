package com.spring.security.config.auth;

import com.spring.security.entity.User;

import lombok.RequiredArgsConstructor;

//post "/login"
//로그인 성공 -> 시큐리티 세션(인증된 UserDetails) -> 시큐리티 context holder에 정보 저장
@RequiredArgsConstructor
public class PrincipalDetails {
	
	private final User user;
	
}