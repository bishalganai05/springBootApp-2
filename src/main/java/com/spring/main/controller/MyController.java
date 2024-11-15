package com.spring.main.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.main.entities.Employee;
import com.spring.main.services.EmployeeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MyController {
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/regPage")
	public String openRegPage(Model model) {
		model.addAttribute("employee",new Employee());
		return "register";
	}
	
	@PostMapping("/regForm")
	public String submitRegForm(@ModelAttribute("employee") Employee employee, Model model) {
		boolean status = employeeService.registerEmployee(employee);
		
		if(status) {
			model.addAttribute("successMsg","Employee Registered Successfully");
		}
		else {
			model.addAttribute("errorMsg","Emplooyee Registration Failed");
		}
		return "register";
	}
	@GetMapping("/loginPage")
	public String openLoginPage(Model model) {
		model.addAttribute("employee",new Employee());
		return "login";
	}
	
	@PostMapping("/loginForm")
	public String submitLoginForm(@ModelAttribute("employee") Employee employee,Model model) {
		Employee validEmployee = employeeService.loginEmployee(employee.getEmail(), employee.getPassword());
		
		if(validEmployee!=null) {
			model.addAttribute("modelName",validEmployee.getName());
			return "profile";
		}
		model.addAttribute("errorMsg","Invalid Login Credentials");
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session!=null) {
			session.invalidate();
		}
		return "redirect:/loginPage";
	}
}
