package com.userms.UserMs.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.userms.UserMs.dao.UserDao;
import com.userms.UserMs.entity.UserEntity;
import com.userms.UserMs.model.UserModel;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserEntity createUser(UserModel userModel) {
		
		UserEntity user = new UserEntity();
		user.setEmail(userModel.getEmail());
		user.setFirstName(userModel.getFirstName());
		user.setLastName(userModel.getLastName());
		user.setPassword(bCryptPasswordEncoder.encode((userModel.getPassword())));
		
		userDao.save(user);
		
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UserEntity userE =  userDao.findByEmail(email);
		
		if(userE == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new User(userE.getEmail(), userE.getPassword(), true, true, true, true, new ArrayList<>()); 
		
	}

}
