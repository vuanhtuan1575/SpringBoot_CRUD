package com.vnpay.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;
import com.vnpay.role.entity.Role;
import com.vnpay.user.util.UserStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "User")
@Getter
@Setter
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "username", nullable = false)
	@Size(min = 3, max = 50)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	private String name;

	private String phone;

	@NotNull
	@Enumerated(EnumType.STRING)
	private UserStatus status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

}
