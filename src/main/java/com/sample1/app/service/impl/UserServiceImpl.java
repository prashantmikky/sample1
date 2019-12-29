package com.sample1.app.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample1.app.UserRepository;
import com.sample1.app.io.entity.UserEntity;
import com.sample1.app.service.UserService;
import com.sample1.app.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDto createUser(UserDto user) {
		
		UserEntity	userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		
		userEntity.setEncryptedPassword("testEncPwd");
		userEntity.setUserId("testUserId");
		
		UserEntity storedUserDetails = userRepository.save(userEntity);
		
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue);
		
		return returnValue;
	}

}