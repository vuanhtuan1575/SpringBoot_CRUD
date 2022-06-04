package com.vnpay.security.service;


import com.vnpay.role.entity.Role;
import com.vnpay.security.dto.UserDetailsDto;
import com.vnpay.user.entity.User;
import com.vnpay.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Qualifier("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        repository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = repository.findByUsernameWithRole(username);

        if (!user.isPresent())
            throw new UsernameNotFoundException("User is not existed");

        Set<GrantedAuthority> authorities = getAuthorities(user.get().getRole());

        return new UserDetailsDto(username, user.get().getPassword(), authorities);

    }

    public Set<GrantedAuthority> getAuthorities(Role role) {
        Set<GrantedAuthority> authorities = new HashSet<>();

        authorities.add(new SimpleGrantedAuthority(role.getName()));

        return authorities;
    }
}
