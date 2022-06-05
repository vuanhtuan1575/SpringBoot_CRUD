package com.vnpay.role.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
	
	@GetMapping("/wellcome")
	public Object welcome() {
		return "Wellcome to Springboot Application";
	}
}
