package com.sample1.app.service.impl;

import java.beans.BeanProperty;
import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sample1.app.io.entity.UserEntity;
import com.sample1.app.repository.UserRepository;
import com.sample1.app.service.UserService;
import com.sample1.app.shared.Utils;
import com.sample1.app.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDto createUser(UserDto user) {
		
		if( userRepository.findByEmail(user.getEmail()) != null) throw new RuntimeException("Record alredy exist");
		UserEntity	userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		
		String userId = utils.generateUserId(30);
		userEntity.setUserId(userId);
		
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		UserEntity storedUserDetails = userRepository.save(userEntity);
		
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue);
		
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if( userEntity == null) throw new UsernameNotFoundException(email);
		
		
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) {
		UserDto returnValue = new UserDto();
		
		UserEntity userEntity = userRepository.findByEmail(email);
		BeanUtils.copyProperties(userEntity, returnValue);
		
		return null;
	}

}
