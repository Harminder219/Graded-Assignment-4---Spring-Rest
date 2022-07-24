package com.greatlearning.controller;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.entity.Roles;
import com.greatlearning.entity.User;
import com.greatlearning.security.SecurityConfiguration;
import com.greatlearning.services.RolesService;
import com.greatlearning.services.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	RolesService rolesService;

	@Autowired
	SecurityConfiguration securityConfiguration;

	@RequestMapping("/home")
	public String homePage() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String name = auth.getName();

		return "Welcome " + name + "!!!";

	}

	@PostMapping("/useradd")
	public String adduser(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("role") String getrole) {

		List<Roles> listroles = new ArrayList<>();

		List<Roles> roles = rolesService.findByName(getrole);

		for (Roles r : roles) {
			listroles.add(r);
		}

		User user = new User();

		user.setUsername(username);
		user.setPassword(securityConfiguration.passwordEncoder().encode(password));
		user.setRoles(listroles);

		userService.addUser(user);

		return "User added succesfully";

	}

}
