package com.vnpay.security.service;

import com.vnpay.common.ultil.ResponseHandler;
import com.vnpay.security.dto.LoginDto;
import com.vnpay.security.entity.SessionManagerEntity;
import com.vnpay.security.jwt.JwtUtils;
import com.vnpay.security.repository.SessionManagerRepository;
import com.vnpay.user.entity.User;
import com.vnpay.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ServiceUserImpl implements ServiceLogin {

	private UserRepository userRepository;
	private SessionManagerRepository sessionManagerRepository;
	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;

	@Autowired
	public ServiceUserImpl(UserRepository userRepository, AuthenticationManager authenticationManager,SessionManagerRepository sessionManagerRepository,
			JwtUtils jwtUtils) {
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
		this.sessionManagerRepository = sessionManagerRepository;
	}

	@Override
	public ResponseEntity<Object> checkLogin(LoginDto loginDto) {
		Optional<User> opUser = userRepository.findByUsername(loginDto.getUsername());
		// check usser exist
		if (!opUser.isPresent()) {
			Map<String, Object> resError = new HashMap<>();
			resError.put("statusCode", "0000A1");
			resError.put("message", "Wrong Username or Password");
			resError.put("result", null);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resError);

		}

		Authentication

		auth = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(auth);
		String token = jwtUtils.generateJwtToken(auth);
		addJwtToken(token, opUser.get().getUsername());
		return ResponseHandler.ResponseCommon(HttpStatus.OK, "Login success", token);

	}

	@Override
	public ResponseEntity<Object> logout(HttpServletRequest request) {
		String jwtTokenFromRequest = jwtUtils.getJwtTokenFromRequest(request);
		if(jwtTokenFromRequest == null) {
			return ResponseHandler.ResponseCommon(HttpStatus.UNAUTHORIZED, "Token is invalid", false);
		}
		
		Optional<SessionManagerEntity> optSession = sessionManagerRepository.findByToken(jwtTokenFromRequest);
		if(!optSession.isPresent()) {
			return ResponseHandler.ResponseCommon(HttpStatus.UNAUTHORIZED, "Token is invalid", false);
		}
		 sessionManagerRepository.delete(optSession.get());
		 return ResponseHandler.ResponseCommon(HttpStatus.OK, "Logout is success", true);
	}
	
	private void addJwtToken(String token, String username) {
	    // add token to database
	    SessionManagerEntity entity = new SessionManagerEntity();
	    entity.setUserName(username);
	    entity.setToken(token);
	    sessionManagerRepository.save(entity);
	}

}
