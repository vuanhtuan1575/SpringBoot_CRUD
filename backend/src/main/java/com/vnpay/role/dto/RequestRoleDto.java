package com.vnpay.role.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestRoleDto {
	
	@NotBlank
	private String name;
	
	private String description;
	
	
}
