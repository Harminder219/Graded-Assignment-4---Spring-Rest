package com.greatlearning.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.greatlearning.entity.Employees;
import com.greatlearning.entity.Roles;
import com.greatlearning.repository.RoleRepository;

@Service
public class RolesService {

	@Autowired
	RoleRepository roleRepository;
	
	public void saveRole(Roles  role)
	{
		roleRepository.save(role);
	}
	
	public List<Roles> findByName(String name) {
		
		Roles roles=new Roles();
		roles.setName(name);
		
        ExampleMatcher exampleMatcher=ExampleMatcher.matching().withMatcher("name", ExampleMatcher.GenericPropertyMatchers.exact()).withIgnorePaths("id");
		
		Example<Roles> example=Example.of(roles,exampleMatcher);
		
		return roleRepository.findAll(example);
		
		
	}
	
}
