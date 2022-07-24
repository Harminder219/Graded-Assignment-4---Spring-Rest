package com.greatlearning.services;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.greatlearning.entity.Employees;

public interface EmployeeService {

	public List<Employees> listAllEmployees();
	
	public Employees findEmplyoyeeById(int id);
	
	public void saveEmployee(Employees employee);
	
	public void deleteEmployee(int id);
	
	public List<Employees> findEmployeeWithUusername(String firstName);
	
	public List<Employees> sortEmployeesWithusername(Direction direction);
}
