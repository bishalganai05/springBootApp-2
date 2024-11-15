package com.spring.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.main.entities.Employee;
import com.spring.main.repository.EmployeeRepository;

@Service
public class EmployeeServicesImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	@Override
	public boolean registerEmployee(Employee employee) {
		boolean status = false;
		try {
			employeeRepository.save(employee);
			status=true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	@Override
	public Employee loginEmployee(String email, String password) {
		Employee validEmployee = employeeRepository.findByEmail(email);
		if(validEmployee!=null && validEmployee.getPassword().equals(password))
			return validEmployee;
		
		return null;
	}

}
