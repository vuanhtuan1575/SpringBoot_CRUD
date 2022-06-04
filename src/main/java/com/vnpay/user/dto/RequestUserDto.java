package com.vnpay.user.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	
	private String roleName;
	

}
