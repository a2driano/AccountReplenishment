package com.account.replenishment.service;

import com.account.replenishment.model.entity.User;
import com.account.replenishment.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

/**
 * @version 1.0
 * @autor a2driano
 * @project: AccountReplenishment
 * @since 15.07.2016
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails userDetails;
        try {
            User user = userRepository.getUserByEmail(email);
            Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
            roles.add(new SimpleGrantedAuthority(user.getUserRole().name()));
            userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), roles);
            return userDetails;
        } catch (EmptyResultDataAccessException e) {
            return userDetails = null;
        }
    }
}
