package com.vnpay.user.service;

import org.springframework.http.ResponseEntity;

import com.vnpay.user.dto.RequestUserDto;
import com.vnpay.user.dto.UpdateDto;

public interface UserService {
	ResponseEntity<Object> saveUser(RequestUserDto userDto);

	ResponseEntity<Object> deleteUser(Long id);
	
	ResponseEntity<Object> updateUser(Long id,UpdateDto updatDto);
	
	ResponseEntity<Object> findUserById(Long id);
	
	ResponseEntity<Object> findUserAll();

}
