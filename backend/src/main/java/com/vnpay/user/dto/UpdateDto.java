package com.vnpay.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateDto {

	private String password;
	
	private String name;

	private String phone;
}
