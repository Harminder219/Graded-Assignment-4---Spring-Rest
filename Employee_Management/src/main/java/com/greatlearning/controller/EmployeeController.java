package com.greatlearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.greatlearning.entity.Employees;
import com.greatlearning.services.EmployeeServiceImpl;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeServiceImpl employeeServiceImpl;

	@RequestMapping("/listall")
	public List<Employees> listAllEmployess() {

		return employeeServiceImpl.listAllEmployees();

	}

	@RequestMapping("/find")
	public String findById(@RequestParam("id") int id) {

		Employees employee = employeeServiceImpl.findEmplyoyeeById(id);

		if (employee != null) {
			return employee.toString();
		}

		return "No employee found";

	}

	@PostMapping("/save")
	public Employees saveEmployee(@ModelAttribute Employees employee) {

		employeeServiceImpl.saveEmployee(employee);

		return employeeServiceImpl.findEmplyoyeeById(employee.getId());

	}

	@PostMapping("/update")
	public String updateEmployee(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email) {

		Employees employee = employeeServiceImpl.findEmplyoyeeById(id);

		if (employee != null) {
			employee.setFirstName(firstName);
			employee.setLastName(lastName);
			employee.setEmail(email);

			employeeServiceImpl.saveEmployee(employee);

			return employeeServiceImpl.findEmplyoyeeById(employee.getId()).toString();

		}

		return "Employee does not exist";

	}

	@RequestMapping("/delete")
	public String deleteEmployee(@RequestParam("id") int id) {

		Employees employee = employeeServiceImpl.findEmplyoyeeById(id);

		if (employee != null) {

			employeeServiceImpl.deleteEmployee(id);

			String str = "Deleted employee id - " + id;

			return str;

		}

		return "Employee does not exist";

	}

	@RequestMapping("/search")
	public String findEmployessByFirstName(@RequestParam("firstName") String firstName) {

		List<Employees> employees = employeeServiceImpl.findEmployeeWithUusername(firstName);

		if (!employees.isEmpty()) {
			return employees.toString();
		}

		return "Employee does not exist";

	}

	@RequestMapping("/sort")
	public List<Employees> sortEmployessByFirstName(@RequestParam("order") String order) {

		Direction direction;

		if (order.equals("ASC")) {
			direction = Direction.ASC;
		} else {
			direction = Direction.DESC;
		}

		return employeeServiceImpl.sortEmployeesWithusername(direction);

	}

	@RequestMapping(value = "403")
	public String invalidoperation() {
		return "You are not authorised to do this operation";
	}

}
