package com.test.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.demo.entities.Role;
import com.test.demo.enums.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByRoleName(RoleName roleName);
}
