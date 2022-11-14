package com.userms.UserMs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userms.UserMs.DTO.UserDTO;
import com.userms.UserMs.controller.model.Employee;
import com.userms.UserMs.dao.EmpServiceImpl;
import com.userms.UserMs.entity.UserEntity;
import com.userms.UserMs.model.UserModel;
import com.userms.UserMs.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private EmpServiceImpl empRepo;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/status/check")
	public String status() {
		return "working";
	}
	
	@GetMapping("/employee/{id}")
	public Employee getEmp(@PathVariable("id") int id) {		
		
		return empRepo.findById(id).get();
	}
	
	@PostMapping("/user")
	public ResponseEntity<UserEntity> createUser(@RequestBody UserModel user) {
		
		
		
		UserEntity userE= userService.createUser(user);
		
		return new ResponseEntity<UserEntity>(userE, HttpStatus.CREATED);
	}

}
