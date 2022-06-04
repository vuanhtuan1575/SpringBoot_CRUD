package com.vnpay.user.repository;

import com.vnpay.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

	@Query("SELECT u FROM User u LEFT JOIN FETCH u.role WHERE u.username = ?1")
	Optional<User> findByUsernameWithRole(String username);
	
}
// Create Entity -> Create Repository > Create Service  > Create Controller

//Service -> create method -> Impl
//create method -> need something 
// ServiceImpl -> Create method Repository
//Controller -> service

