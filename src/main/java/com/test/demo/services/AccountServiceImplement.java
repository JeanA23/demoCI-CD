package com.test.demo.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.demo.entities.Role;
import com.test.demo.entities.User;
import com.test.demo.repositories.RoleRepository;
import com.test.demo.repositories.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImplement implements AccountService, UserDetailsService{

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		return userRepository.findByEmail(email)
				.orElseThrow(()-> new UsernameNotFoundException("Cet utilisateur n'existe pas"));
	}

	@Override
	public Role addNewRole(Role role) {
		
		return roleRepository.save(role);
	}
 
	@Override
	public void addRolesToUser(User user, List<Role> roles) {

		User userFromDb = userRepository.findByEmail(user.getEmail()).orElse(null);
		
		roles.stream()
		.map(Role::getRoleName) //récupère les roles de la liste
		.map(roleRepository::findByRoleName) //Vérifier que le role existe dans la base de donnée
		.forEach(userFromDb.getRoles()::add);// ajouter à la liste des rôles de l’utilisateur
		
	}

}
