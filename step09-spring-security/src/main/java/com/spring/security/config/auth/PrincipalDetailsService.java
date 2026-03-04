package com.spring.security.config.auth;

import org.springframework.stereotype.Service;

import com.spring.security.repository.UserRepository;

import lombok.RequiredArgsConstructor;

//인증 실제 진행 -> loadUserByUsername
@RequiredArgsConstructor
@Service
public class PrincipalDetailsService {

	private final UserRepository userRepository;
	
	// 시큐리티 내부에서 실제 인증 --> 성공적이라면? return UserDetails

}
