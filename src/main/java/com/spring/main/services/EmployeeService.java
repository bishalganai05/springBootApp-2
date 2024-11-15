package com.spring.main.services;

import com.spring.main.entities.Employee;

public interface EmployeeService {
	public boolean registerEmployee(Employee employee);
	public Employee loginEmployee(String email,String password);
}
