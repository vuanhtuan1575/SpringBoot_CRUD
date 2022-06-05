package com.vnpay.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class RequestUserDto {
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String username;
	
	private String name;
	
	private String phone;

	@NotBlank
	private String roleName;
	

}
