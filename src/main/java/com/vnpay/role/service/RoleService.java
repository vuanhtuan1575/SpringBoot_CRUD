package com.vnpay.role.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.vnpay.role.dto.RequestRoleDto;
import com.vnpay.role.entity.Role;


public interface RoleService {
	ResponseEntity<Object> saveRole(RequestRoleDto roleDto);

	List<Role> findAll();
	
}
