package com.vnpay.security.controller;

import com.vnpay.common.Constants;
import com.vnpay.common.ultil.ResponseHandler;
import com.vnpay.security.dto.LoginDto;
import com.vnpay.security.service.ServiceLogin;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private static final Logger logger = LoggerFactory.getLogger(Jwts.class);

	private ServiceLogin serviceLogin;

	public AuthController(ServiceLogin serviceLogin) {
		this.serviceLogin = serviceLogin;
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> login(@Valid @RequestBody LoginDto dto, BindingResult errors) {
		if (errors.hasErrors())
			return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

		try {

			return serviceLogin.checkLogin(dto);

		} catch (Exception e) {
			logger.debug("{} has been logged in with wrong password: {}", dto.getUsername(), e.getMessage());
			ResponseEntity<Object> objectResponseEntity = ResponseHandler.ResponseCommon(HttpStatus.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR,
					false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(objectResponseEntity);
		}
	}

	@GetMapping("/logout")
	public ResponseEntity<Object> getLogoutPage(HttpServletRequest request) {
		try {
			return serviceLogin.logout(request);
		} catch (Exception e) {
			ResponseEntity<Object> objectResponseEntity = ResponseHandler.ResponseCommon(HttpStatus.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR,
					false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(objectResponseEntity);
		}
	}

}
