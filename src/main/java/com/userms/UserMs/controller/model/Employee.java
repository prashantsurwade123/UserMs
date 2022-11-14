package com.userms.UserMs.controller.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "emp")
@Entity
public class Employee {

	@Column(name = "emp_id")
	@Id
	public int emp_id;
	@Column(name = "emp_name")
	public String emp_name;
	@Column(name = "emp_address")
	public String emp_addess;

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getEmp_addess() {
		return emp_addess;
	}

	public void setEmp_addess(String emp_addess) {
		this.emp_addess = emp_addess;
	}

}
