package com.greatlearning.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.greatlearning.entity.Employees;
import com.greatlearning.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl  implements EmployeeService{

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public List<Employees> listAllEmployees() {
	
		return employeeRepository.findAll();
	}

	@Override
	public Employees findEmplyoyeeById(int id) {
		// TODO Auto-generated method stub
		return employeeRepository.findById(id).get();
	}

	@Override
	public void saveEmployee(Employees employee) {
	  
		employeeRepository.save(employee);
		
	
		
	}

	@Override
	public void deleteEmployee(int id) {
		
		employeeRepository.deleteById(id);
		
	}

	@Override
	public List<Employees> findEmployeeWithUusername(String firstName) {
		
		Employees employee = new Employees();
		employee.setFirstName(firstName);
		ExampleMatcher exampleMatcher=ExampleMatcher.matching().withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.exact()).withIgnorePaths("id","lastName","email");
		
		Example<Employees> example=Example.of(employee,exampleMatcher);
		
		return employeeRepository.findAll(example);
		
	}

	@Override
	public List<Employees> sortEmployeesWithusername(Direction direction) {
		
		return employeeRepository.findAll(Sort.by(direction, "firstName"));
	}

}
