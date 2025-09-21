package com.test.demo.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountResponseDto {

	private String message;
	private String token;
}
