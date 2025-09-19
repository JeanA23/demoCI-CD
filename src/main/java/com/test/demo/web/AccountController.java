package com.test.demo.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.demo.dtos.AccountResponseDto;
import com.test.demo.dtos.LoginRequestDTO;
import com.test.demo.dtos.RegisterRequestDTO;
import com.test.demo.services.AutenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/t1/account")
@RequiredArgsConstructor
public class AccountController {

	private final AutenticationService autenticationService;
	
	
	@PostMapping("/register")
	public ResponseEntity<AccountResponseDto> register(@RequestBody RegisterRequestDTO registerUser) {
		
		return autenticationService.register(registerUser);
	}
	
	@PostMapping("/login")
	public ResponseEntity<AccountResponseDto> login(@RequestBody LoginRequestDTO loginUser) {
		
		return autenticationService.login(loginUser);
	}
}
