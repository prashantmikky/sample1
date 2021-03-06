package com.sample1.app.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.sample1.app.shared.dto.UserDto;

public interface UserService extends UserDetailsService{

	UserDto createUser(UserDto user);
	UserDto getUser(String email);
	
}

