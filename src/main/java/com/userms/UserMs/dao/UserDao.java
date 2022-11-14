package com.userms.UserMs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.userms.UserMs.entity.UserEntity;


@Repository
public interface UserDao extends JpaRepository<UserEntity, Integer> {

	UserEntity findByEmail(String email);
	

}
