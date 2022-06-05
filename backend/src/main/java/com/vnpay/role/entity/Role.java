package com.vnpay.role.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.vnpay.user.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
	@Size(min = 3, max = 50)
	@Column(unique = true, nullable = false)
	private String name;
	
	@Column(name ="description" , nullable = true)
	private String description;
	
	@OneToMany(mappedBy="role", fetch = FetchType.LAZY)
    private Set<User> users;
	

	
}
