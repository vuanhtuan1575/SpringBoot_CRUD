package com.vnpay.security.service;

import com.vnpay.security.dto.LoginDto;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface ServiceLogin {

	
	public ResponseEntity<Object> checkLogin(LoginDto loginDto);
	
	public ResponseEntity<Object> logout(HttpServletRequest request);
	
	
	
}
