package com.userms.UserMs.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.userms.UserMs.entity.UserEntity;
import com.userms.UserMs.model.UserModel;

public interface UserService extends UserDetailsService{
	
	public UserEntity createUser(UserModel userModel);

}
