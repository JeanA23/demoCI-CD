package com.test.demo.services;

import java.util.List;

import com.test.demo.entities.Role;
import com.test.demo.entities.User;

public interface AccountService {

	Role addNewRole(Role role);
	
	void addRolesToUser(User user, List<Role> role);
}
