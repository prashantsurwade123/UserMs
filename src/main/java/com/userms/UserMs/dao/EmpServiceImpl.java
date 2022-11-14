package com.userms.UserMs.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.userms.UserMs.controller.model.Employee;

@Repository
public interface EmpServiceImpl extends CrudRepository<Employee, Integer>{

}
