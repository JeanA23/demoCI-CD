package com.test.demo.services;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.demo.dtos.AccountResponseDto;
import com.test.demo.dtos.LoginRequestDTO;
import com.test.demo.dtos.RegisterRequestDTO;
import com.test.demo.entities.User;
import com.test.demo.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutenticationService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	
	
	public ResponseEntity<AccountResponseDto> register(RegisterRequestDTO registerUser) {
		
		if(userRepository.findByEmail(registerUser.getEmail()).isPresent()) {
			
			return ResponseEntity.badRequest()
					.body(AccountResponseDto.builder()
							.message("Cet utilisateur existe déjà avec cette adresse email")
							.build());
		}
		
		

		var user = User.builder()
				.firstname(registerUser.getFirstname())
				.lastname(registerUser.getLastname())
				.email(registerUser.getEmail())
				.password(passwordEncoder.encode(registerUser.getPassword()))
				.build();
		
		userRepository.save(user);
		
		return ResponseEntity.ok(AccountResponseDto.builder()
				.message("Utilisateur enrégistré avec succès")
				.build());
	}

	
	public ResponseEntity<AccountResponseDto> login(LoginRequestDTO loginUser) {
		
			
			var user = userRepository.findByEmail(loginUser.getEmail()).orElseThrow(
					
					() -> new UsernameNotFoundException("Utilisateur introuvable"));
			
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(
							
							loginUser.getEmail(), 
							loginUser.getPassword()
									
									));
			
			var jwtToken = jwtService.generateToken(user);
			
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Acces-Control-Expose-Headers", "Authorization");
			responseHeaders.add("Authorization", "Bearer " + jwtToken);
			
			
		return ResponseEntity.ok()
				.headers(responseHeaders)
				.body(AccountResponseDto.builder()
						.message("Utilisateur authentifié avec succès")
						.token(jwtToken)
						.build());
		
	}
}
