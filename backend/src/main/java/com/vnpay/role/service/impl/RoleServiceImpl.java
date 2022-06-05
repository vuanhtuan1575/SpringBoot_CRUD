package com.vnpay.role.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vnpay.role.dto.RequestRoleDto;
import com.vnpay.role.entity.Role;
import com.vnpay.role.repository.RoleRepository;
import com.vnpay.role.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {
	private RoleRepository roleRepository;
	
	
	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	
	
	}
	
	
	@Override
	@Transactional
	public ResponseEntity<Object> saveRole(RequestRoleDto roleDto) {
		 	Role newRole= new Role();
	        newRole.setName(roleDto.getName());
	        newRole.setDescription(roleDto.getDescription());
	        Role saveRole = roleRepository.save(newRole);
			return null;
	}
	
	 public List<Role> findAll() {
	        return roleRepository.findAll();
	    }

	   

}
