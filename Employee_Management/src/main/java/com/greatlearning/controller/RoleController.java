package com.greatlearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.entity.Roles;
import com.greatlearning.services.RolesService;

@RestController
public class RoleController {

	@Autowired
	RolesService rolesService;

	@PostMapping("/addrole")
	public String addrole(@ModelAttribute Roles role) {

		rolesService.saveRole(role);

		return "Role added successfully";
	}

	@RequestMapping("/getrole")

	public List<Roles> getrole(String name) {
		return rolesService.findByName(name);
	}
}
