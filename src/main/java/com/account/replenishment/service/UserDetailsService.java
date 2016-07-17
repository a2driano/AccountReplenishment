package com.account.replenishment.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @version 1.0
 * @autor a2driano
 * @project: AccountReplenishment
 * @since 16.07.2016
 */
public interface UserDetailsService {
    UserDetails loadUserByUsername(String login) throws UsernameNotFoundException;
}
