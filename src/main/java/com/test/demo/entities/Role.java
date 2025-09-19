package com.test.demo.entities;

import com.test.demo.enums.RoleName;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Role {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private RoleName roleName;
}
