package com.test.demo.dtos;

import java.util.List;

import lombok.Data;

@Data
public class RegisterRequestDTO {

	
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private List<String> role;
}
